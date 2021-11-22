import peasy.*;

PShape rocket;
GraphDraw gd;
float ry;
PeasyCam cam;

  //D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj
public void setup() {
  size(1024, 768, P3D);
  FileReader fr = new FileReader("D:\\GIT\\Processing\\subdivision3d\\subdivision\\cube.obj");
  Mesh mesh = fr.readFile();
  mesh.initialize();
  gd = new GraphDraw(mesh);
  cam = new PeasyCam(this, width/2, height/2, 0, 250);
  cam.setMinimumDistance(250);
  cam.setMaximumDistance(400);
}

int cameraX = 0;

boolean[] keysPressed = new boolean[256];

boolean isKeyPressed(char id){
  boolean isPressed = false;
  if(keyPressed){
    if(key == id){
      keysPressed[id] = true;
    }
  } else {
    isPressed = keysPressed[id];
    keysPressed[id] = false;
  }
  return isPressed;
}

public void draw() {
  if (isKeyPressed('g')) {
    gd.applyCatmullClark();
  } else if (isKeyPressed('d')) {
    gd.applyDooSabin();
  } else if(isKeyPressed('r')) {
    gd.revert();
  } else if(isKeyPressed('o')){
    selectFolder("Select a folder to process:", "folderSelected");
  }
 
  background(255);
  gd.draw();
}


void folderSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    Export.exportToObj(gd.getMesh(), selection.getAbsolutePath(), "export");
    
  }
}
