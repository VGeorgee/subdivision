

public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D add(Point3D p){
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public Point3D subtract(Point3D p){
        return new Point3D(x - p.x, y - p.y, z - p.z);
    }

    public Point3D multiply(Point3D p){
        return new Point3D(x * p.x, y * p.y, z * p.z);
    }

    public Point3D multiply(double d){
        return new Point3D(x * d, y * d, z * d);
    }

    public Point3D divide(Point3D p){
        return new Point3D(x / p.x, y / p.y, z / p.z);
    }

    public Point3D divide(double d){
        return new Point3D(x / d, y / d, z / d);
    }

    public Point3D clone(){
        return new Point3D(x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
