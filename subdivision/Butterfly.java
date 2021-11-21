
import java.util.ArrayList;

public class Butterfly {
    private Mesh original;
    private Mesh reCalculated;

    private ArrayList<Integer> indices;


    double[] weights;

    public Butterfly(double a, double b, double c){
        weights = new double[] { a, b, c};
    }


    public Mesh reCalculate(Mesh original){
        this.original = original;
       // this.reCalculated = new Mesh(original.isQuadratic);
       // this.reCalculated.vertices.addAll(original.vertices);
       // this.reCalculated.halfEdges.addAll(original.halfEdges);/// ???
        indices = new ArrayList<>();
        for(int i = 0; i < original.halfEdges.size();i++){
            indices.add(-1);
        }

        generateEdgeVertices();
        connectEdgeVertices();
        this.original.initialize();

        return this.original;
    }

    private void generateEdgeVertices(){
        for(int i = 0; i < original.halfEdges.size(); i++){
            if(indices.get(i) == -1){

                HalfEdge edge1_2 = original.halfEdges.get(i);
                HalfEdge edge2_3 = original.halfEdges.get(edge1_2.next);
                HalfEdge edge3_1 = original.halfEdges.get(edge1_2.prev);
                HalfEdge edge1_4 = original.halfEdges.get(original.halfEdges.get(edge1_2.pairHalfEdgeIndex).next);
                HalfEdge edge4_2 = original.halfEdges.get(original.halfEdges.get(edge1_2.pairHalfEdgeIndex).prev);
                HalfEdge edge5_4 = original.halfEdges.get(original.halfEdges.get(edge1_4.pairHalfEdgeIndex).prev);
                HalfEdge edge6_2 = original.halfEdges.get(original.halfEdges.get(edge4_2.pairHalfEdgeIndex).prev);
                HalfEdge edge7_3 = original.halfEdges.get(original.halfEdges.get(edge2_3.pairHalfEdgeIndex).prev);
                HalfEdge edge8_1 = original.halfEdges.get(original.halfEdges.get(edge3_1.pairHalfEdgeIndex).prev);

                Vertex v1 = original.vertices.get(edge1_2.startVertexIndex);
                Vertex v2 = original.vertices.get(edge2_3.startVertexIndex);
                Vertex v3 = original.vertices.get(edge3_1.startVertexIndex);
                Vertex v4 = original.vertices.get(edge4_2.startVertexIndex);
                Vertex v5 = original.vertices.get(edge5_4.startVertexIndex);
                Vertex v6 = original.vertices.get(edge6_2.startVertexIndex);
                Vertex v7 = original.vertices.get(edge7_3.startVertexIndex);
                Vertex v8 = original.vertices.get(edge8_1.startVertexIndex);

                Vertex a = (v1.add(v2)).multiply(weights[0]);
                Vertex b = (v3.add(v4)).multiply(weights[1]);
                Vertex c = (v5.add(v6).add(v7).add(v8)).multiply(-weights[2]);

                Vertex newVertex = a.add(b).add(c);

                this.original.vertices.add(newVertex);
                indices.set(i, original.vertices.size() - 1);
                indices.set(original.halfEdges.get(i).pairHalfEdgeIndex, original.vertices.size() - 1);

            }
        }
    }


    private void connectEdgeVertices() {
        original.faces = new ArrayList<>();
        for(int i = 0; i < original.halfEdges.size(); i += 3){
            HalfEdge edge = original.halfEdges.get(i);

            Face f1 = new Face(
                    edge.startVertexIndex,
                    indices.get(i),
                    indices.get(edge.prev)
            );

            Face f2 = new Face(
                    indices.get(i),
                    original.halfEdges.get(edge.next).startVertexIndex,
                    indices.get(edge.next)
            );

            Face f3 = new Face(
                    original.halfEdges.get(edge.prev).startVertexIndex,
                    indices.get(edge.prev),
                    indices.get(edge.next)
            );

            Face f4 = new Face(
                    indices.get(edge.prev),
                    indices.get(i),
                    indices.get(edge.next)
            );

            this.original.faces.add(f1);
            this.original.faces.add(f2);
            this.original.faces.add(f3);
            this.original.faces.add(f4);
        }
    }


}
