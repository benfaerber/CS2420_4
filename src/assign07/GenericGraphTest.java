package assign07;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericGraphTest {
    private Graph<String> simpleGraph, simpleDag, graphFromSlides;

    @BeforeEach
    void setUp() throws Exception {
        this.simpleGraph = GraphParser.parseGraphFromFile("src/assign07/exampleGraph.txt");
        this.simpleDag = GraphParser.parseGraphFromFile("src/assign07/exampleDag.txt");
        this.graphFromSlides = GraphParser.parseGraphFromFile("src/assign07/toposortTwoPossible.txt");
    }

    @AfterEach
    void tearDown() throws Exception {}


    @Test
    void testParseGraph() {
        Graph<String> sample = new Graph<String>();
        sample.addEdge("a", "b");
        sample.addEdge("b", "c");
        sample.addEdge("c", "d");
        sample.addEdge("b", "d");
        sample.addEdge("d", "a");

        String exampleDot = sample.generateDot();

        Graph<String> graph = GraphParser.parseGraph(exampleDot);
        assertEquals(exampleDot, graph.generateDot());
    }

    @Test
    void testDepthFirstSearch() {
        Graph<String> a = GraphParser.parseGraphFromFile("src/assign07/exampleGraph.txt");

        assertTrue(this.simpleGraph.depthFirstSearch("a", "d"));
        assertFalse(this.simpleDag.depthFirstSearch("d", "a"));
    }

   @Test
   void testBreadthFirstSearch() {
        Graph<String> a = GraphParser.parseGraphFromFile("src/assign07/exampleDag.txt");
        List<String> foundPath = new ArrayList<>();
        foundPath.add("a");
        foundPath.add("b");
        foundPath.add("d");
        assertEquals(foundPath, a.breadthFirstSearch("a", "d"));
   }

   @Test
   void testTopoSort(){
        List<String> expectedPathUniq = new ArrayList<>();
        expectedPathUniq.add("a");
        expectedPathUniq.add("b");
        expectedPathUniq.add("c");
        expectedPathUniq.add("d");
        assertEquals(expectedPathUniq, this.simpleDag.topoSort());
   }

   @Test
   void testTopoSortFromSlides() {
        List<String> expectedSort1 = new ArrayList<>(Arrays.asList(
                "a", "c", "e", "f", "h", "g"
        ));
        assertEquals(expectedSort1, this.graphFromSlides.topoSort());
   }


}




