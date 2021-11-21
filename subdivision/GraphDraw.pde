
class GraphDraw {
 View view;
 public GraphDraw(Mesh original){
   this.view = View.getInstance(original);
 }
 
 public void draw(){
   
    translate(width/2, height/2, 0);
    lights();
    //scale(8.0);
    int scale = 50;
    Mesh mesh = view.getMesh();
    System.out.println(mesh.vertices.size() + " " + mesh.faces.size() + " " + mesh.halfEdges.size());
    for(Face face: mesh.faces){
      fill(0);
      stroke(50);
      beginShape();
      for(int i = 0; i < 4; i++){
        Point3D p= mesh.vertices.get(face.vertexIndices[i]).vertex;
        
        vertex((float)p.getX() * scale, (float)p.getY() * scale, (float)p.getZ() * scale);
      }
      endShape();
    }
 }
 
 public void revert(){
   view.revert();
 }
     public void applyButterfly(){
        view.applyButterfly();
    }

    public void applyCatmullClark(){
        view.applyCatmullClark();
    }
    
    public void applyDooSabin(){
        view.applyCatmullClark();
    }
}
