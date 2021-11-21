
class GraphDraw {
 View view;
 public GraphDraw(View view){
   this.view = view;
 }
 
 public void draw(){
   
    translate(width/2, height/2, 0);
lights();
//scale(8.0);
int scale = 10;
    Mesh mesh = view.getMesh();
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
 
 
}
