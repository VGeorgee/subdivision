

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private Scanner file;

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
        if(tokens.length != 5) {
            System.out.println(line);
        }
        int a = Integer.parseInt(tokens[1]) - 1;
        int b = Integer.parseInt(tokens[2]) - 1;
        int c = Integer.parseInt(tokens[3]) - 1;
        int d = Integer.parseInt(tokens[4]) - 1;
        return new Face(a, b, c, d);
    }


}
