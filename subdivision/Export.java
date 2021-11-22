
import java.io.FileWriter;
import java.io.IOException;

public class Export {

    public static void exportToObj(Mesh mesh, String absoluteFilePath, String fileName){
        try {
            FileWriter writer = new FileWriter(absoluteFilePath + fileName + ".obj");

            for(Vertex vertex: mesh.vertices) {
                writer.write("v " + vertex.getVertex().toString() + "\n");
            }

            for(Face face: mesh.faces) {
                String faceString = "f";
                for(int i = 0; i < face.vertexIndices.length; i++) {
                    faceString += " " + (face.vertexIndices[i] + 1);
                }
                if(face != mesh.faces.get(mesh.faces.size() - 1)) {
                    faceString += "\n";
                }
                writer.write(faceString);
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
