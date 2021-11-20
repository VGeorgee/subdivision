PShape rocket;
GraphDraw gd;
float ry;
  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(640, 360, P3D);
  FileReader fr = new FileReader("D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  rocket = loadShape("cube.obj");
  gd = new GraphDraw(mesh);
}

public void draw() {
  
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
