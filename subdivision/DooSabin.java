public class DooSabin {
    private Mesh mesh;
    private Mesh newMesh;

    int V;
    int F;
    int H;
    int E;

    int[] EM;

    public Mesh reCalculate(Mesh mesh) {
        this.mesh = mesh;
        this.newMesh = new Mesh(true);

        beforeAlgorithmStarts();
        computeNewFacePoints();
        computeNewEdgeVertices();
        computeNewVertexPoints();
        newMesh.initialize();
        return newMesh;
    }
    void beforeAlgorithmStarts() {
        V = mesh.vertices.size();
        F = mesh.faces.size();
        H = mesh.halfEdges.size();;
        E = H / 2;
        EM = new int[H];
        for(int i = 0; i < H; i++){
            EM[i] = -1;
        }
        System.out.println(V + " " + F + " " + H + " " + E);
    }

    void computeNewFacePoints() {
        for (int i = 0; i < mesh.faces.size(); i++){
            newMesh.vertices.add(getFacePoint(i));
        }
    }

    Vertex getFacePoint(int faceIndex) {
        Vertex v0 = mesh.vertices.get(mesh.faces.get(faceIndex).vertexIndices[0]);
        Vertex v1 = mesh.vertices.get(mesh.faces.get(faceIndex).vertexIndices[1]);
        Vertex v2 = mesh.vertices.get(mesh.faces.get(faceIndex).vertexIndices[2]);
        Vertex v3 = mesh.vertices.get(mesh.faces.get(faceIndex).vertexIndices[3]);

        return (v0.add(v1).add(v2).add(v3)).multiply(0.25);
    }

    void computeNewEdgeVertices() {
        for(int i = 0; i < mesh.halfEdges.size(); i++){
            if(EM[i] == -1){
                int faceIndex = mesh.halfEdges.get(i).faceIndex;

                int v1 = mesh.halfEdges.get(i).startVertexIndex;
                int v2 = mesh.halfEdges.get(mesh.halfEdges.get(i).next).startVertexIndex;

                Vertex edge1 = mesh.vertices.get(v1);
                Vertex edge2 = mesh.vertices.get(v2);

                Vertex edgePoint = (edge1.add(edge2)).multiply(0.5);

                newMesh.vertices.add(edgePoint);
                EM[i] = newMesh.vertices.size() - 1;
                EM[mesh.halfEdges.get(i).pairHalfEdgeIndex] = newMesh.vertices.size() - 1;
            }
        }
    }

    void computeNewVertexPoints(){
        for (int i = 0; i < mesh.vertices.size(); i++){
            Vertex S = mesh.vertices.get(i);
            int n = S.valence;

            Vertex a = new Vertex();
            Vertex b = new Vertex();
            Vertex c = new Vertex();
            Vertex d = new Vertex();

            for (int j = 0; j < n; ++j) {
                int heIndex = S.halfEdgeIndices.get(j);
                int f = mesh.halfEdges.get(heIndex).faceIndex;
                a = getFacePoint(f);

                b = mesh.vertices.get(mesh.halfEdges.get(heIndex).startVertexIndex);
                d = mesh.vertices.get(mesh.halfEdges.get(mesh.halfEdges.get(heIndex).next).startVertexIndex);

                int f_point = mesh.faces.get(f).vertexIndices[0];
                c = mesh.vertices.get(f_point);
                Vertex newVertex = (a.add(b).add(c).add(d)).divide(4.0);
                newMesh.vertices.add(newVertex);
            }

            int[] v = new int[4];
            for (int j = 0; j < n; ++j) {
                int heIndex = S.halfEdgeIndices.get(j);
                int f = mesh.halfEdges.get(heIndex).faceIndex;

                int v1 = mesh.halfEdges.get(heIndex).startVertexIndex;
                int v2 = mesh.halfEdges.get(mesh.halfEdges.get(heIndex).next).startVertexIndex;

                v[0] = newMesh.vertices.size() - 1;
                v[1] = EM[heIndex];
                v[2] = f;
                v[3] = EM[mesh.halfEdges.get(heIndex).next];

                Face face = new Face(v[0], v[1], v[2], v[3]);
                newMesh.faces.add(face);
            }
        }
    }
}
