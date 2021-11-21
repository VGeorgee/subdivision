

import java.util.Stack;

public class View {
    private Stack<Mesh> meshes;
    private Butterfly butterfly;

    public View(Mesh mesh) {
        meshes = new Stack<>();
        meshes.push(mesh);
        butterfly = new Butterfly(1.0/2.0, 1.0/8.0, 1.0/16.0);
    }

    public Mesh getMesh() {
        return meshes.peek();
    }

    public void revert(){
        meshes.pop();
    }

    public void applyButterfly(){
        meshes.push(butterfly.reCalculate(getMesh()));
    }

}
