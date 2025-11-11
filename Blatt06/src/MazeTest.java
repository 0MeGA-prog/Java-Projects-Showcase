import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class MazeTest {

	@Test
	void testMazeIntInt() {
		// Maze mit 3x3 = 9 Knoten, Start bei 0
		assertDoesNotThrow(() -> {
			Maze maze = new Maze(3, 0);
			assertEquals(9, maze.M().V());  // 3x3 = 9 nodes
		});
	}

	@Test
	void testAddEdge() {
		Maze maze = new Maze(3, 0);

		int v = 6, w = 8;
		assertFalse(maze.hasEdge(v, w)); // sollte am Anfang nicht verbunden sein
		maze.addEdge(v, w);
		assertTrue(maze.hasEdge(v, w)); // jetzt verbunden
	}

	@Test
	void testHasEdge() {
		Maze maze = new Maze(3, 0);

		maze.addEdge(0, 1);
		assertTrue(maze.hasEdge(0, 1));
		assertTrue(maze.hasEdge(1, 0));  // ungerichtet
		assertFalse(maze.hasEdge(0, 2));
		assertTrue(maze.hasEdge(0, 0));  // reflexiv laut Aufgabenstellung
	}

	@Test
	void testMazegrid() {
		Maze maze = new Maze(3, 0);
		Graph G = maze.mazegrid();

		assertTrue(G.adj(0).contains(1));
		assertTrue(G.adj(0).contains(3));
		assertTrue(G.adj(4).contains(1));
		assertTrue(G.adj(4).contains(3));
		assertTrue(G.adj(4).contains(5));
		assertTrue(G.adj(4).contains(7));
	}

	@Test
	void testFindWay() {
		Maze maze = new Maze(3, 3);
		maze.addEdge(0, 1);
		maze.addEdge(1, 2);
		maze.addEdge(0, 3);
		maze.addEdge(3, 4);
		maze.addEdge(4, 1);
		maze.addEdge(4, 5);
		maze.addEdge(5, 2);

		List<Integer> path = maze.findWay(0, 2);
		assertNotNull(path);
		assertFalse(path.isEmpty());
		assertEquals(0, (int) path.get(0));                // Startpunkt
		assertEquals(2, (int) path.get(path.size() - 1));  // Endpunkt
	}
}
