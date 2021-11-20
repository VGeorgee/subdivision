
import java.util.ArrayList;
import java.util.HashMap;

public class Mesh {
    ArrayList<Point3D> originals;

    ArrayList<Vertex> vertices;
    ArrayList<Face> faces;
    ArrayList<HalfEdge> halfEdges;


    public Mesh(){
        originals = new ArrayList<>();
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
        halfEdges = new ArrayList<>();
    }

    public Mesh(ArrayList<Point3D> list ){
        originals = list;
    }

    public void setOriginals(ArrayList<Point3D> points){
        originals = points;
    }

    public void addVertex(Vertex v){
        this.vertices.add(v);
    }

    public void addFace(Face f){
        this.faces.add(f);
    }


    public void initialize(){
        HashMap<String, Integer> halfEdgeID = new HashMap<>();

        for(Face face: this.faces){
            for(int i = 0; i < 4; i++){
                String key = face.vertexIndices[i] + " " + face.vertexIndices[(i+1) % 4];
                halfEdgeID.put(key, halfEdgeID.size());
            }
        }

        this.halfEdges.ensureCapacity(halfEdgeID.size()+1);
        for(int i = 0; i < halfEdgeID.size(); i++){
            this.halfEdges.add(null);
        }

        int fi = 0;
        for(Face face: this.faces){
            int vi[] = face.vertexIndices;


            for(int i = 0; i < 4; i++) {
                Vertex vertex = this.vertices.get(vi[i]);
                vertex.valence++;

                int heID = halfEdgeID.get(vi[i] + " " + vi[(i + 1) % 4]);
                int hePID = halfEdgeID.get(vi[(i + 1) % 4] + " " + vi[i]);
                int heNext = halfEdgeID.get(vi[(i + 1) % 4] + " " + vi[(i + 2) % 4]);
                int hePrev = halfEdgeID.get(vi[(i + 3) % 4] + " " + vi[i]);
                vertex.halfEdgeIndices.add(heID);
                System.out.println(heID);
                this.halfEdges.set(heID, new HalfEdge(hePID, fi++, vi[i], heNext, hePrev));
            }




        }
    }

    @Override
    public String toString() {
        return "Mesh{" +
                "originals=" + originals +
                ", vertices=" + vertices +
                ", faces=" + faces.toString() +
                ", halfEdges=" + halfEdges +
                '}';
    }
}
