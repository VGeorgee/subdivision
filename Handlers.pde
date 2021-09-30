void mouseClicked(){
  if(mouseButton == LEFT){
    if(mouseY < BUTTON_SIZE){
      buttonClicked(mouseX);
    } else {
      Point p = new Point(mouseX, mouseY);
      println(p);
      shape.add(p);
    }
  }
}
