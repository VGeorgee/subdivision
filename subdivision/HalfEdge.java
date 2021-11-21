
public class HalfEdge {
    int pairHalfEdgeIndex;
    int faceIndex;
    int startVertexIndex;
    int prev;
    int next;


    public HalfEdge(){
        this.pairHalfEdgeIndex = -1;
    }

    public HalfEdge(int pairHalfEdgeIndex, int faceIndex, int startVertexIndex, int next, int prev) {
        this.pairHalfEdgeIndex = pairHalfEdgeIndex;
        this.faceIndex = faceIndex;
        this.startVertexIndex = startVertexIndex;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HalfEdge halfEdge = (HalfEdge) o;
        return pairHalfEdgeIndex == halfEdge.pairHalfEdgeIndex;
    }

}
