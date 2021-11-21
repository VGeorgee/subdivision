

import java.util.ArrayList;
import java.util.HashMap;

public class Mesh {
    ArrayList<Point3D> originals;
    ArrayList<Vertex> vertices;
    ArrayList<Face> faces;
    ArrayList<HalfEdge> halfEdges;
    boolean isQuadratic;

    public Mesh(){
        originals = new ArrayList<>();
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
        halfEdges = new ArrayList<>();
    }

    public Mesh(boolean isQuadratic){
        this();
        this.isQuadratic = isQuadratic;
    }

    public void addVertex(Vertex v){
        this.vertices.add(v);
    }

    public void addFace(Face f){
        this.faces.add(f);
    }


    public void initialize(){
        HashMap<String, Integer> halfEdgeID = new HashMap<>();

        int length = isQuadratic ? 4 : 3;

        for(Face face: this.faces){
            for(int i = 0; i < 4; i++){
                String key = face.vertexIndices[i] + " " + face.vertexIndices[(i+1) % 4];
                halfEdgeID.put(key, halfEdgeID.size());
                this.vertices.get(face.vertexIndices[i]).valence = 0;
                this.vertices.get(face.vertexIndices[i]).clear();
            }
        }

        this.halfEdges = new ArrayList<>();
        this.halfEdges.ensureCapacity(halfEdgeID.size()+1);
        for(int i = 0; i < halfEdgeID.size(); i++){
            this.halfEdges.add(null);
        }

        int fi = 0;
        for(Face face: this.faces){
            int vi[] = face.vertexIndices;


            for(int i = 0; i < length; i++) {
                Vertex vertex = this.vertices.get(vi[i]);
                vertex.valence++;

                try {
                    int heID = halfEdgeID.get(vi[i] + " " + vi[(i + 1) % length]);
                    vertex.halfEdgeIndices.add(heID);

                    int hePID = halfEdgeID.get(vi[(i + 1) % length] + " " + vi[i]);
                    int heNext = halfEdgeID.get(vi[(i + 1) % length] + " " + vi[(i + 2) % length]);
                    int hePrev = halfEdgeID.get(vi[(i + (length - 1)) % length] + " " + vi[i]);

                    this.halfEdges.set(heID, new HalfEdge(hePID, fi, vi[i], heNext, hePrev));
                    //face.halfEdgeIndices[i] = heID;
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }

            fi++;
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
