import java.util.Objects;

public class Pair<E> {
    // TODO
// implementierung zweier Elemente mit beliebigen typen
    private E first;
    private E second;

    // Konstruktor (initialisierung)
    public Pair(E first, E second){
        this.first = first;
        this.second = second;
    }

    // Kopierkonstruktor (?)
    public Pair(Pair<E> kopie){
        this.first = kopie.first;
        this.second = kopie.second;
    }

    // getter/setter first+second
    public E getFirst() {
        return first;
    }
    public E getSecond() {
        return second;
    }

    public void setFirst(E first) {
        this.first = first;
    }
    public void setSecond(E second) {
        this.second = second;
    }

    // von der Zeile mit Swap (pair1.swap();)
    public void swap() {
        E temp = first;
        first = second;
        second = temp;
    }

    // durch Generate-Menü erzeugter equals/hashCode und toString auf semantische Gleichheit
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair<?> pair = (Pair<?>) object;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair<" + first + ", " + second + ">";
    }

    public static void main(String[] args) {
        Pair<Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer> pair2 = new Pair<>(1, 2);
        System.out.println("Variable pair1 hat den Wert: " + pair1);
        System.out.println("Variable pair2 hat den Wert: " + pair2);
        System.out.println("Syntaktische Gleichheit von pair1 und pair2 ist: " + (pair1==pair2));
        System.out.println("Semantische Gleichheit von pair1 und pair2 ist: " + pair1.equals(pair2));
        Pair<Integer> pair1b = pair1;
        Pair<Integer> pair2b = new Pair<>(pair2);
        pair1.swap();
        pair2.setFirst(10);
        System.out.println("Nach swap() hat Variable pair1 den Wert: " + pair1);
        System.out.println("Nach setFirst(10) hat Variable pair2 den Wert: " + pair2);
        System.out.println("Die zuvor erstellte Kopie pair1b hat den Wert: " + pair1b);
        System.out.println("Die zuvor erstellte Kopie pair2b hat den Wert: " + pair2b);
        /*
        Die erwartete Ausgabe ist:
Variable pair1 hat den Wert: Pair<1, 2>
Variable pair2 hat den Wert: Pair<1, 2>
Syntaktische Gleichheit von pair1 und pair2 ist: false
Semantische Gleichheit von pair1 und pair2 ist: true
Nach swap() hat Variable pair1 den Wert: Pair<2, 1>
Nach setFirst(10) hat Variable pair2 den Wert: Pair<10, 2>
Die zuvor erstellte Kopie pair1b hat den Wert: Pair<2, 1>
Die zuvor erstellte Kopie pair2b hat den Wert: Pair<1, 2>
         */
    }
}
