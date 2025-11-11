import java.util.LinkedList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases = 1;

	void initialize() {
		n1 = 4;
		n2 = 6;
		Cases c = new Cases();
		p1 = c.switchforTesting(cases, n1);
		p2 = c.switchforTesting(cases, n2);
	}


	@Test
	void testPermutation() {
		initialize();
		// TODO
		testVariation(p1, n1);
		testVariation(p2, n2);
	}

	void testVariation(PermutationVariation p, int n) {        //erstelle methode um die länge und duplikate der werte zu überprüfen
		assertEquals(n, p.original.length, "Prüfe ob länge korrekt initialisiert ist");
		for (int i = 0; i < p.original.length; i++) {
			for (int j = i + 1; j < p.original.length; j++) {
				assertNotEquals(p.original[i], p.original[j], "Doppeltes Element gefunden");
			}
		}
		assertNotNull(p.allDerangements, "Liste von Derangements darf nicht null sein");
	}

	@Test
	void testDerangements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();    //erzeugung fixpunktfreien Permutationen (derangements)
		p2.derangements();

		checkDerangement(p1);    // überprüfung auf korrektheit der Permutationen
		checkDerangement(p2);
	}

	void checkDerangement(PermutationVariation p) {    // methode zur überprüfung
		int countDerangements = derangementsNumber(p.original.length);	// Erwartete Anzahl der Derangements anhand der Formel
		assertEquals(countDerangements, p.allDerangements.size(), "Anzahl berechneter Derangements entspricht nicht Erwartung");
		for (int[] derangement : p.allDerangements) {    // for-each-Schleife- p.allDerangements wird in[] übergeben
			assertTrue(isADerangement(p.original, derangement), "Derangement ungültig");
		}
	}
	// Prüft ob kein Element an gleichen positionen
		boolean isADerangement(int[] original, int[] derangement){
			if(original.length != derangement.length) {
				return false;
			}
			for(int i = 0; i < original.length; i++) {
					if (original[i] == derangement[i]) {
						return false;	// fixpunkte gefunden
					}
			}
			return true;
		}
		int derangementsNumber(int n){        // berechnungsformel
			double sum = 0;
			for (int i = 0; i <= n; i++){
				sum += (Math.pow(-1, i) / factorial(i));
			}
			return (int) (factorial(n) * sum);
		}

	int factorial(int n){	//Fakultät-Formel
		if (n <= 1) {
			return 1;
		} // else{
			return n * factorial(n - 1);
		// }
	}
	
	@Test
	void testsameElements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();
		p2.derangements();
		// test zur überprüfung erzeugter Permutationen mit original
		testElements(p1);
		testElements(p2);
	}
	// prüfe ob Permutation nur aus originalelementen besteht
	void testElements(PermutationVariation p){
		assertFalse(p.allDerangements.isEmpty(), "Keine Derangements zum Testen"); //keine Derangements zum Testen
		for(int[] derangement : p.allDerangements){
			assertTrue(isPermutation(p.original, derangement), "Derangement enthält andere Elemente");	//Gültiges Derangement
		}
	}
	boolean isPermutation(int[] original, int[] permutation){
		int[] sortedOriginal = Arrays.copyOf(original, original.length);
		int[] sortedPermutation = Arrays.copyOf(permutation, permutation.length);

		Arrays.sort(sortedOriginal);
		Arrays.sort(sortedPermutation);
		//Vergleich derangement mit original
		return Arrays.equals(sortedOriginal, sortedPermutation);
	}



	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


