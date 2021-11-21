
import java.util.ArrayList;

public class Vertex {
    Point3D vertex;
    int halfEdgeIndex;
    int valence;
    ArrayList<Integer> halfEdgeIndices;

    public Vertex() {
        vertex = new Point3D();
        halfEdgeIndex = -1;
        valence = 0;
        halfEdgeIndices = new ArrayList<>();
    }

    public Vertex(Point3D vertex) {
        this.vertex = vertex;
        halfEdgeIndex = -1;
        valence = 0;
        halfEdgeIndices = new ArrayList<>();
    }

    public Vertex(Point3D vertex, int index) {
        this.vertex = vertex;
        halfEdgeIndex = index;
        valence = 0;
        halfEdgeIndices = new ArrayList<>();
    }

    public void clear(){
        halfEdgeIndices.clear();
    }


    public Vertex add(Vertex v) {
        return new Vertex(this.vertex.add(v.vertex), halfEdgeIndex);
    }

    public Vertex multiply(Vertex v){
        return new Vertex(this.vertex.multiply(v.vertex), halfEdgeIndex);
    }

    public Vertex multiply(double d){
        return new Vertex(this.vertex.multiply(d), halfEdgeIndex);
    }

    public Vertex divide(Vertex v){
        return new Vertex(this.vertex.divide(v.vertex), halfEdgeIndex);
    }

    public Point3D getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "vertex=" + vertex +
                ", halfEdgeIndex=" + halfEdgeIndex +
                ", valence=" + valence +
                ", halfEdgeIndices=" + halfEdgeIndices +
                '}';
    }
}
