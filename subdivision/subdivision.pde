PShape rocket;
GraphDraw gd;
float ry;
  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(640, 360, P3D);
  FileReader fr = new FileReader("D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  mesh.initialize();
  
  View.getInstance(mesh);
  
    System.out.println(mesh.vertices.size() + " " + mesh.faces.size() + " " + mesh.halfEdges.size());
  gd = new GraphDraw(view);
  gd.draw(); 
  //view.applyButterfly();
  mesh = view.getMesh();
  background(255);
  
  gd.draw(); 
    System.out.println(mesh.vertices.size() + " " + mesh.faces.size() + " " + mesh.halfEdges.size());
}
int cameraMove = 0;

public void draw() {
  delay(50);
  if (keyPressed) {
    if (key == 'b') {
      cameraMove += 10;
    } else if (key == 'a') {
      cameraMove -= 10;
    } else if (key == 'g') {
      view.applyCatmullClark();
    } else if(key == 'r') {
      view.revert();
    }
  } 
  //rect(25, 25, 50, 50);
  background(255);
  camera(0, 0, 0, width/2, height/2, cameraMove, 0, 1, 0);
  gd.draw();
  
  /*
  background(0);
  lights();
  translate(width/2, height/2 + 100, -200);
  scale(64.0);
  rotateZ(PI);
  rotateY(ry);
  shape(rocket);
  
  ry += 0.02;
*/
}
