package org.example;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class word_graphshort_Test {

    private word_graph app;

    @Before
    public void setUp() {
        app = new word_graph();
        app.graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // 构建简单图： a -> b -> c
        app.graph.addVertex("a");
        app.graph.addVertex("b");
        app.graph.addVertex("c");

        DefaultWeightedEdge ab = app.graph.addEdge("a", "b");
        app.graph.setEdgeWeight(ab, 1.0);

        DefaultWeightedEdge bc = app.graph.addEdge("b", "c");
        app.graph.setEdgeWeight(bc, 1.0);
    }

    @Test
    public void testQueryBridgeWords_Typical() {
        String result = app.queryBridgeWords("a", "c");
        assertEquals("The bridge words from \"a\" to \"c\" are: \"b\".", result);
        System.out.println("测试一通过");
    }

    @Test
    public void testQueryBridgeWords_Word1NotFound() {
        String result = app.queryBridgeWords("x", "c");
        assertEquals("No \"x\" in the graph!", result);
        System.out.println("测试二通过");
    }

    @Test
    public void testQueryBridgeWords_Word2NotFound() {
        String result = app.queryBridgeWords("a", "x");
        assertEquals("No \"x\" in the graph!", result);
        System.out.println("测试三通过");
    }

    @Test
    public void testQueryBridgeWords_BothNotFound() {
        String result = app.queryBridgeWords("x", "y");
        assertEquals("No \"x\" or \"y\" in the graph!", result);
        System.out.println("测试四通过");
    }

    @Test
    public void testQueryBridgeWords_NoBridge() {
        app.graph.addVertex("d");
        app.graph.addEdge("a", "d"); // no bridge to "d"
        String result = app.queryBridgeWords("a", "d");
        assertEquals("No bridge words from \"a\" to \"d\"!", result);
        System.out.println("测试五通过");
    }

    @Test
    public void testQueryBridgeWords_CaseInsensitive() {
        String result = app.queryBridgeWords("A", "C");
        assertEquals("The bridge words from \"a\" to \"c\" are: \"b\".", result);
        System.out.println("测试六通过");
    }

//    @Test
//    public void testQueryBridgeWords_EmptyStrings() {
//        String result = app.queryBridgeWords("", "");
//        assertEquals("No \"\" or \"\" in the graph!", result);
//    }
//
//    @Test
//    public void testQueryBridgeWords_SameWord() {
//        String result = app.queryBridgeWords("a", "a");
//        assertEquals("No bridge words from \"a\" to \"a\"!", result);
//    }
}
