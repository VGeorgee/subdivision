
Shape shape;

void setup(){
  test();
  
  size(400, 400);
  shape = new Shape();
  drawButtons();
  
}

void draw(){
  
}

void test(){
  if(!(subdivisionTest1() && subdivisionTest2()  && subdivisionTest3())){
    throw new Error("tests failed");
  }
}
