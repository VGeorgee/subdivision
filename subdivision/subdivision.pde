PShape rocket;
GraphDraw gd;
View view;
float ry;
  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(640, 360, P3D);
  FileReader fr = new FileReader("D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  view = new View(mesh);
  rocket = loadShape("cube.obj");
  gd = new GraphDraw(view);
}
int cameraMove = 0;

public void draw() {
  
  if (keyPressed) {
    if (key == 'b') {
      cameraMove++;
    } else if (key == 'a') {
      cameraMove--;
    } else if (key == 'g') {
      view.applyButterfly();
    }
  } 
  rect(25, 25, 50, 50);
  background(255);
  //camera(mouseX, mouseY, cameraMove, 0, height/2, 0, 0, 1, 0);
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
