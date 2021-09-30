class Point  {
    private int x;
    private int y;

    public Point (double x, double y) {
        this.x = (int)x;
        this.y = (int)y;        
    }


    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public float getDistance(Point p){
        return sqrt(squared(p.getX() - this.x) + squared(p.getY() - this.y));
    }

    private int squared(int value){
        return value * value;
    }
    
    boolean equals(Object o){
      Point p = (Point) o;
      return this.x == p.getX() && this.y == p.getY();
    }

    String toString(){
        return this.x + " " + this.y;
    }
}
