PShape rocket;
GraphDraw gd;
float ry;
  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(640, 360, P3D);
  FileReader fr = new FileReader("C:\\Users\\Kiri\\Desktop\\dev\\subdivision\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  mesh.initialize();
  gd = new GraphDraw(mesh);
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
      gd.applyCatmullClark();
    } else if (key == 'd') {
      gd.applyDooSabin();
    } else if(key == 'r') {
      gd.revert();
    }
  } 
  //rect(25, 25, 50, 50);
  background(255);
  camera(0, cameraMove, 0, width/2, height/2, 0, 0, 1, 0);
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
