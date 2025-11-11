import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class RandomDepthFirstPathsTest {

	@Test
	void testRandomDFS() {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);

		RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(g, 0);
		rdfp.randomDFS(g);

		assertTrue(rdfp.hasPathTo(5));
		assertThrows(IllegalArgumentException.class, () -> rdfp.hasPathTo(99));
	}

	@Test
	void testRandomNonrecursiveDFS() {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);

		RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(g, 0);
		rdfp.randomNonrecursiveDFS(g);

		assertTrue(rdfp.hasPathTo(3));
		assertThrows(IllegalArgumentException.class, () -> rdfp.hasPathTo(99));
	}

	@Test
	void testPathTo() {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);

		RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(g, 0);
		rdfp.randomDFS(g);

		List<Integer> path = rdfp.pathTo(3);
		assertNotNull(path);
		assertEquals(0, (int) path.get(0));
		assertEquals(3, (int) path.get(path.size() - 1));
		assertTrue(path.contains(1));
		assertTrue(path.contains(2));
	}
}
