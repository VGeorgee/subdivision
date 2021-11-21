
import java.util.Stack;

public class View {
    private Stack<Mesh> meshes;
    private Butterfly butterfly;
    private CatmullClark catmullClark;
    private DooSabin dooSabin;


    private static View INSTANCE = null;

    public static View getInstance(Mesh mesh){
        if(INSTANCE == null){
            INSTANCE = new View(mesh);
        }
        return INSTANCE;
    }

    private View(Mesh mesh) {
        meshes = new Stack<>();
        meshes.push(mesh);
        butterfly = new Butterfly(1.0/2.0, 1.0/8.0, 1.0/16.0);
        catmullClark = new CatmullClark();
    }

    public Mesh getMesh() {
        return meshes.peek();
    }

    public void revert(){
        if(meshes.size() > 1){
            meshes.pop();
        }
    }

    public void applyButterfly(){
        meshes.push(butterfly.reCalculate(getMesh()));
    }

    public void applyCatmullClark(){
        meshes.push(catmullClark.reCalculate(getMesh()));
    }
    
    
    public void applyDooSabin(){
        meshes.push(dooSabin.reCalculate(getMesh()));
    }
}
