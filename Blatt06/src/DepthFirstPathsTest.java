import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class DepthFirstPathsTest {

	@Test
	void testDfs() {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		// Kein Pfad zu Knoten 5
		DepthFirstPaths dfs = new DepthFirstPaths(g, 0);
		dfs.dfs(g);

		assertTrue(dfs.hasPathTo(4));
		assertFalse(dfs.hasPathTo(5)); // Gültiger Knoten, aber unerreichbar
	}

	@Test
	void testNonrecursiveDFS() {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		// Knoten 3 ist isoliert
		DepthFirstPaths dfs = new DepthFirstPaths(g, 0);
		dfs.nonrecursiveDFS(g);

		assertTrue(dfs.hasPathTo(2));
		assertFalse(dfs.hasPathTo(3)); // Gültiger Knoten, aber nicht verbunden
	}

	@Test
	void testPathTo() {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);

		DepthFirstPaths dfs = new DepthFirstPaths(g, 0);
		dfs.dfs(g);

		List<Integer> path = dfs.pathTo(3);
		assertNotNull(path);
		assertEquals(List.of(0, 1, 2, 3), path);
	}
}
