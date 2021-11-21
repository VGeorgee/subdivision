
import java.util.Arrays;

public class Face {
    int[] vertexIndices;
    int[] halfEdgeIndices;

    public Face() {
        vertexIndices = new int[4];
        halfEdgeIndices = new int[4];
    }

    public Face(int a, int b, int c) {
        vertexIndices = new int[]{a, b, c, 0};
        halfEdgeIndices = new int[4];
    }

    public Face(int a, int b, int c, int d) {
        vertexIndices = new int[]{a, b, c, d};
        halfEdgeIndices = new int[4];
    }


    @Override
    public String toString() {
        return "Face{" +
                "vertexIndices=" + Arrays.toString(vertexIndices) +
                ", halfEdgeIndices=" + Arrays.toString(halfEdgeIndices) +
                '}';
    }
}
