import peasy.*;

PShape rocket;
GraphDraw gd;
float ry;
PeasyCam cam;

  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(640, 360, P3D);
  FileReader fr = new FileReader("C:\\Users\\Kiri\\Desktop\\dev\\subdivision\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  mesh.initialize();
  gd = new GraphDraw(mesh);
  cam = new PeasyCam(this, width/2, height/2, 0, 250);
  cam.setMinimumDistance(250);
  cam.setMaximumDistance(400);
}

int cameraX = 0;

public void draw() {
  delay(50);
  if (keyPressed) {
    if (key == 'g') {
      gd.applyCatmullClark();
    } else if (key == 'd') {
      gd.applyDooSabin();
    } else if(key == 'r') {
      gd.revert();
    } else if(key == 'o'){
      selectFolder("Select a folder to process:", "folderSelected");
    }
  } 
  
  background(255);
  gd.draw();
}


void folderSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
  }
}
