public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        FileReader fr = new FileReader("C:\\Users\\Kiri\\Desktop\\dev\\subdivision\\subdivision\\cube.obj");
        Mesh mesh = fr.readFile();
        mesh.initialize();

        /*Butterfly bf = new Butterfly(1.0/2.0, 1.0/8.0, 1.0/16.0);
        mesh = bf.reCalculate(mesh); */

        DooSabin ds = new DooSabin();
        mesh = ds.reCalculate(mesh);

        /*
        CatmullClark cm = new CatmullClark();
        cm.reCalculate(mesh);
*/
/*
        for(Face face: mesh.faces){
            System.out.println("line starts");
            for(int i = 0; i < 4; i++){
                Vertex v= mesh.vertices.get(face.vertexIndices[i]);
                System.out.println(v);
            }
            System.out.println("line ends");
        }
*/

    }
}
