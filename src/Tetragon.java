// TODO
public class Tetragon extends ConvexPolygon{

    //Konstruktor für vier eckpunkte
    public Tetragon(Vector2D a, Vector2D b, Vector2D c, Vector2D d){
        super(new Vector2D[]{a, b, c, d}); // gibt diese als Array an konstruktor ConvexPoly weiter
    }

}
