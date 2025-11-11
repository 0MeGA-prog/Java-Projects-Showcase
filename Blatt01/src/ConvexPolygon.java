import java.util.Arrays;


public class ConvexPolygon extends Polygon {
    // TODO
    public ConvexPolygon(Vector2D[] vertices) {
        this.vertices = vertices;

    }
    @Override
    public double perimeter() {
        double result = 0.0;
        int n = vertices.length;
        for (int i = 0; i < n; i++) {
            Vector2D a = vertices[i];
            Vector2D b = vertices[(i + 1) % n];
            double dx = b.getX() - a.getX();
            double dy = b.getY() - a.getY();
            result += Math.sqrt(dx * dx + dy * dy);
        }
        return result;
    }
    @Override
    public double area() {
        double totalArea = 0.0;
        for (int i = 1; i < vertices.length - 1; i++) {
            Vector2D a = vertices[0];
            Vector2D b = vertices[i];
            Vector2D c = vertices[i + 1];

            double ab = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
            double bc = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
            double ca = Math.sqrt(Math.pow(a.getX() - c.getX(), 2) + Math.pow(a.getY() - c.getY(), 2));

            double s = (ab + bc + ca) / 2.0;
            double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
            totalArea += area;
        }
        return totalArea;
    }
    public static double totalArea(Polygon[] polygons) {
        double result = 0.0;
        for (Polygon p : polygons) {
            result += p.area();
        }
        return result;
    }
    public static Polygon[] somePolygons() {
        Polygon[] array = new Polygon[4];
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);

        array[0] = new Triangle(a, b, c);
        Vector2D d = new Vector2D(0, 0);
        Vector2D e = new Vector2D(10, -5);
        Vector2D f = new Vector2D(12, 2);
        Vector2D g = new Vector2D(3, 17);

        array[1] = new Tetragon(a, b, c, d);
        array[2] = new RegularPolygon(5, 1);
        array[3] = new RegularPolygon(6, 1);

        ConvexPolygon polygon = new ConvexPolygon(new Vector2D[] {a, b, c});
        System.out.println(polygon);

        return array;
    }
    @Override
    public String toString() {
        return "ConvexPolygon[" + Arrays.toString(vertices) + "]";
    }
}



