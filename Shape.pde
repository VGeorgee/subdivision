class Shape {
    ArrayList<Point> points;

    public Shape() {
        this.points = new ArrayList<>();
    }

    public void add(Point p){
        points.add(p);
    }
    
    public void draw(){
        int size = this.points.size();
        if(size < 3){
            return;
        }
        background(255);
        Point a, b;
        println("-------------------------------------------------");
        for (int i = 1; i <= size; ++i) {
            a = this.points.get(i - 1 );
            b = this.points.get(i % size);
            line(a.getX(), a.getY(), b.getX(), b.getY());
            println(a);
        }
    }

    public void subdivide(){
        ArrayList<Point> newPoints = new ArrayList<>();

        
        for(int i = 1; i <= this.points.size(); i++){
            Point a = this.points.get(i - 1);
            Point b = this.points.get(i%this.points.size());

            //newPoints.add(a);
            Point[] subdivided = this.getSubdivided(a, b, 0.25, 0.75);
            newPoints.add(subdivided[0]);
            newPoints.add(subdivided[1]);
        }
        this.points = newPoints;
        this.draw();
    }

    private Point[] getSubdivided(Point a, Point b, double wA, double wB){
        Point[] points = new Point[2];

        double axa = a.getX() * wA;
        double aya = a.getY() * wA;
        
        double bxa = b.getX() * wA;
        double bya = b.getY() * wA;

        double axb = a.getX() * wB;
        double ayb = a.getY() * wB;
        
        double bxb = b.getX() * wB;
        double byb = b.getY() * wB;
  
        double pax = axa + bxb;
        double pay = aya + byb;
        
        double pbx = axb + bxa;
        double pby = ayb + bya;
    
        points[0] = new Point(pbx, pby);
        points[1] = new Point(pax, pay);
        
        return points;
    }

  void reset(){
    background(255);
    this.points = new ArrayList<>();
  }
}
