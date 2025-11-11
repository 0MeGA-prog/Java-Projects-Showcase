import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    int[][] matrix; // Matrix zum Speichern der Zwischenlösungen
    int[] values;   // Anzahl der Murmeln in jeder Schüssel


    public RowOfBowls() {
    }
    
    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values)
    {
        // TODO
        this.values = values;   // Initialisierung der Klassendaten
        matrix = new int[values.length][values.length];
        if (values.length == 0) {   // Spezialfall: Leeres Spielfeld
            return 0;
        }
        // Initialisiere die Diagonale der Matrix (nur eine Schüssel zur Auswahl)
        IntStream.range(0, values.length).forEach(i -> matrix[i][i] = values[i]);
        // Fülle die Matrix für alle möglichen Bereiche (von Länge 2 bis n)
        IntStream.range(2, values.length + 1).forEach(pars ->
                IntStream.range(0, values.length - pars + 1).forEach(left -> {
                    int right = left + pars - 1;
                    matrix[left][right] = Math.max(values[left] - matrix[left + 1][right],
                            values[right] - matrix[left][right - 1]);
                })
        );
        return matrix[0][values.length - 1];    // maximale Differenz
    }

    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values) {
        // TODO
        // BiFunction = rekursiv über linke/ rechte Indizes iterieren
        BiFunction<Integer, Integer, Integer> maxGainRecursive = new BiFunction<>() {
            @Override
            public Integer apply(Integer left, Integer right) {
                if (values.length == 0) {   // Spezialfall: leeres Array
                    return 0;
                }
                if (left.equals(right)) {   // Ein Schüssel übrig, diese nehmen
                    return values[left];
                }
                // Spieler wählt links/ rechts. Gegner spielt optimal weiter
                return Math.max(
                        values[left] - this.apply(left + 1, right),
                        values[right] - this.apply(left, right - 1));
            }
        };
        // Starte mit gesamtem bereich
        return maxGainRecursive.apply(0, values.length - 1);
    }

    
    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence() {
        // TODO
        ArrayList<Integer> sequence = new ArrayList<>();
        final int[] chooseLeft = new int[1];    // Zwischenspeicher
        // Rekursive Funktion zum Zurückverfolgen der optimalen Züge (DP-Matrix)
        BiFunction<Integer, Integer, Void> lookForSequence = new BiFunction<>() {
            @Override
            public Void apply(Integer left, Integer right) {    // Bereich ungültig
                if (left > right) {
                    return null;
                }
                // Ein Schüssel, fügen zur Sequenz hinzu
                if (left.equals(right)) {
                    sequence.add(left);
                    return null;
                }
                // Berechne Wert, wenn Spieler links wählt
                if (left + 1 <= right) {
                    chooseLeft[0] = values[left] - matrix[left + 1][right];
                } else {
                    chooseLeft[0] = values[left];   // letzte mögliche Wahl
                }
                // Vergleich gespeicherte lösung (matrix), linker Zug optimal?
                if (matrix[left][right] == chooseLeft[0]) {
                    sequence.add(left); // links gewählt
                    this.apply(left + 1, right);    // Restbereich
                } else {
                    sequence.add(right);    // rechts gewählt
                    this.apply(left, right - 1);
                }
                return null;
            }
        };
        // Rückverfolgung gesamten Bereich
        lookForSequence.apply(0, values.length - 1);
        // finale Zugfolge zurück
        return sequence;

    }


    public static void main(String[] args)
    {
        // For Testing
        
        }
}

