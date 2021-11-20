
class GraphDraw {
 Mesh mesh;
 public GraphDraw(Mesh mesh){
   this.mesh = mesh;
 }
 
 public void draw(){
   
    translate(width/2, height/2, 0);
    scale(32.0);
    for(Face face: mesh.faces){
      fill(0);
      stroke(255);
      beginShape();
      for(int i = 0; i < 4; i++){
        Point3D p= mesh.vertices.get(face.vertexIndices[i]).vertex;
        
        vertex((float)p.getX(), (float)p.getY(), (float)p.getZ());
      }
      endShape();
    }
 }
 
 
}
