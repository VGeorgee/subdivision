
boolean subdivisionTest1(){
  Point expectedA = new Point(12, 12);
  Point expectedB = new Point(17, 17);
  Point a = new Point(10, 10);
  Point b = new Point(20, 20);
  Shape s = new Shape();
  
  Point[] result = s.getSubdivided(a, b, 0.25, 0.75);
  
  return result[0].equals(expectedA) && result[1].equals(expectedB);
}


boolean subdivisionTest2(){
  Point expectedA = new Point(6, 17);
  Point expectedB = new Point(7, 15);
  Point a = new Point(6, 18);
  Point b = new Point(8, 14);
  Shape s = new Shape();
  
  Point[] result = s.getSubdivided(a, b, 0.25, 0.75);
  
  return result[0].equals(expectedA) && result[1].equals(expectedB);
}

boolean subdivisionTest3(){
  Point expectedA = new Point(7, 15);
  Point expectedB = new Point(6, 16);
  Point a = new Point(6, 18);
  Point b = new Point(8, 14);
  Shape s = new Shape();
  
  Point[] result = s.getSubdivided(a, b, 0.65, 0.35);
  
  return result[0].equals(expectedA) && result[1].equals(expectedB);
}
