
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private Scanner file;
    private boolean isQuadratic;

    public FileReader(String fileName){
        try{
            this.file = new Scanner(new File(fileName));
            System.out.println("beolvasva");
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public Mesh readFile(){
        Mesh mesh = new Mesh();

        while(file.hasNextLine()){

            String line = file.nextLine();

            if(line.startsWith("v ")){
                mesh.addVertex(convertToVertex(line));
            } else if( line.startsWith("f ")) {
                mesh.addFace(convertToFace(line));
            }

        }
        mesh.isQuadratic = isQuadratic;
        return mesh;
    }

    private Vertex convertToVertex(String line){
        String[] tokens = line.split(" ");
        if(tokens.length != 4) {
            System.out.println(line);
        }
        double x = Double.parseDouble(tokens[1]);
        double y = Double.parseDouble(tokens[2]);
        double z = Double.parseDouble(tokens[3]);
        Point3D p = new Point3D(x, y, z);
        return new Vertex(p);
    }


    private Face convertToFace(String line){
        String[] tokens = line.split(" ");

        if(tokens.length == 5){
            isQuadratic = true;
            return buildQuadraticFace(tokens);
        }
        isQuadratic = false;
        return buildFace(tokens);
    }

    private Face buildQuadraticFace(String [] tokens){
        int a = Integer.parseInt(tokens[1]) - 1;
        int b = Integer.parseInt(tokens[2]) - 1;
        int c = Integer.parseInt(tokens[3]) - 1;
        int d = Integer.parseInt(tokens[4]) - 1;
        // TODO! Also run by getVertex()
        return new Face(a, b, c, d);
    }

    private Face buildFace(String [] tokens){
        int a = getVertex(tokens[1]);
        int b = getVertex(tokens[2]);
        int c = getVertex(tokens[3]);
        return new Face(a, b, c);
    }

    private int getVertex(String faceToken){
        String[] subTokens = faceToken.split("/");
        return Integer.parseInt(subTokens[0]);
    }


}
