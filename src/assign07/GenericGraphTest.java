package assign07;
import static org.junit.jupiter.api.Assertions.*;

import assign06.LinkedListStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericGraphTest {
    private Graph<String> simpleGraph, exampleDag;

    @BeforeEach
    void setUp() throws Exception {
        this.simpleGraph = GraphParser.parseGraphFromFile("src/assign07/exampleGraph.txt");
        this.exampleDag = GraphParser.parseGraphFromFile("src/assign07/exampleDag.txt");
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

        System.out.println("Depth First Search");
        System.out.println(a);

        assertTrue(this.simpleGraph.depthFirstSearch("a", "d"));
        assertFalse(this.exampleDag.depthFirstSearch("d", "a"));
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



}




