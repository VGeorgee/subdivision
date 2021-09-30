String[] buttons = {"reset", "divide", "draw"};
int BUTTON_SIZE = 60;
void drawButtons(){
  
  textSize(14);
  for(int i = 0; i < buttons.length; i++){
    fill(128);
    square(i * BUTTON_SIZE, 0, BUTTON_SIZE);
    fill(255);
    //textAlign(CENTER);
    text(buttons[i], (i) * (BUTTON_SIZE) , BUTTON_SIZE / 2);
  }
}

void buttonClicked(int y){
   switch(y / BUTTON_SIZE){
     case 0: shape.reset(); break;
     case 1: shape.subdivide(); break;
     case 2: shape.draw(); break;
   }
   drawButtons();
}
