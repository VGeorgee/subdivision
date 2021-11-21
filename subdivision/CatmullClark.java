


public class CatmullClark {
    Mesh original;
    Mesh newMesh;
    double[] weights;

    int V;
    int F;
    int H;
    int E;
    int[] EM;

    public CatmullClark(){
        this(0.25, 0.25, 0.25, 0.25);
    }

    public CatmullClark(double a, double b, double c, double d){
        weights = new double[] { a, b, c, d};
    }

    public Mesh reCalculate(Mesh original){

        this.original = original;
        this.newMesh = new Mesh();
        this.newMesh.isQuadratic = true;

        beforeAlgorithmStarts();
        computeNewFacePoints();
        computeNewEdgeVertices();
        computeNewVertexPoints();
        newMesh.initialize();
        return newMesh;
    }


    void beforeAlgorithmStarts() {
        V = original.vertices.size();
        F = original.faces.size();
        H = original.halfEdges.size();;
        E = H / 2;
        EM = new int[H];
        for(int i = 0; i < H; i++){
            EM[i] = -1;
        }
        System.out.println(V + " " + F + " " + H + " " + E);
    }

    void computeNewFacePoints() {
        for (int i = 0; i < original.faces.size(); i++){
            newMesh.vertices.add(getFacePoint(i));
        }
    }

    Vertex getFacePoint(int faceIndex) {
        Vertex v0 = original.vertices.get(original.faces.get(faceIndex).vertexIndices[0]);
        Vertex v1 = original.vertices.get(original.faces.get(faceIndex).vertexIndices[1]);
        Vertex v2 = original.vertices.get(original.faces.get(faceIndex).vertexIndices[2]);
        Vertex v3 = original.vertices.get(original.faces.get(faceIndex).vertexIndices[3]);

        return (((v0.multiply(weights[0])).add(v1.multiply(weights[1]))).add(v2.multiply(weights[2]))).add((v3.multiply(weights[3])));
    }

    void computeNewEdgeVertices() {

        for(int i = 0; i < original.halfEdges.size(); i++){
            if(EM[i]==-1){
                int faceIndex = original.halfEdges.get(i).faceIndex;

                int v1 = original.halfEdges.get(i).startVertexIndex;
                int v2 = original.halfEdges.get(original.halfEdges.get(i).next).startVertexIndex;

                Vertex edge1 = original.vertices.get(v1);
                Vertex edge2 = original.vertices.get(v2);

                int edgePair = original.halfEdges.get(i).pairHalfEdgeIndex;
                Vertex face1 = getFacePoint(faceIndex );

                Vertex face2 = getFacePoint(original.halfEdges.get(edgePair).faceIndex);

                Vertex edgePoint = face1.multiply(weights[0]).add(face2.multiply(weights[1])).add(edge1.multiply(weights[2])).add(edge2.multiply(weights[3]));

                newMesh.vertices.add(edgePoint);
                EM[i] = newMesh.vertices.size() - 1;
                EM[original.halfEdges.get(i).pairHalfEdgeIndex] = newMesh.vertices.size() - 1;
            }
        }
    }

    void computeNewVertexPoints(){
        for (int i = 0; i < original.vertices.size(); i++){
            Vertex S = original.vertices.get(i);
            int n = S.valence;

            Vertex Q = new Vertex();
            Vertex R = new Vertex();


            for(int j = 0; j < n; j++){
                int heIndex = S.halfEdgeIndices.get(j);
                int f = original.halfEdges.get(heIndex).faceIndex;
                Q = Q.add(getFacePoint(f));

                int v1 = original.halfEdges.get(heIndex).startVertexIndex;
                int v2 = original.halfEdges.get(original.halfEdges.get(heIndex).next).startVertexIndex;

                R = R.add( (original.vertices.get(v1).add(original.vertices.get(v2))).divide(2.0) );

            }

            Q = Q.divide((double)n);
            R = R.divide((double)n);

            Vertex newVertex = (Q.add(R.multiply(2)).add(S.multiply((double)n - 3))).divide((double)n);
            newMesh.vertices.add(newVertex);

            int[] v = new int[4];

            for(int j = 0; j < n; j++){
                int heIndex = S.halfEdgeIndices.get(j);
                int f = original.halfEdges.get(heIndex).faceIndex;


                v[0] = newMesh.vertices.size() - 1;
                v[1] = EM[heIndex];
                v[2] = f;
                v[3] = EM[original.halfEdges.get(heIndex).prev];

                Face face = new Face(v[0], v[1], v[2], v[3]);
                newMesh.faces.add(face);
            }
        }
    }
}
