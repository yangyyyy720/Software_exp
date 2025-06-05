package org.example;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class word_graaphTestwhite {

    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
    private word_graph app;

    @BeforeEach
    void setUp() {
        app = new word_graph();
        graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // 构建测试图
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");

        graph.setEdgeWeight(graph.addEdge("a", "b"), 1.0);
        graph.setEdgeWeight(graph.addEdge("b", "c"), 2.0);
        graph.setEdgeWeight(graph.addEdge("c", "a"), 3.0);
        graph.setEdgeWeight(graph.addEdge("a", "d"), 1.0);
        graph.setEdgeWeight(graph.addEdge("d", "e"), 1.0);
        graph.setEdgeWeight(graph.addEdge("e", "c"), 1.0);
    }

    @Test
    void testCalcShortestPath_NormalCase() {
        System.out.println("Running testCalcShortestPath_NormalCase...");
        String result = app.calcShortestPath(graph, "a", "c");
        try {
            assertEquals("a -> b -> c", result);
            System.out.println("testCalcShortestPath_NormalCase: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_NormalCase: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_SameWord() {
        System.out.println("Running testCalcShortestPath_SameWord...");
        String result = app.calcShortestPath(graph, "a", "a");
        try {
            assertEquals("a", result);
            System.out.println("testCalcShortestPath_SameWord: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_SameWord: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_DirectEdge() {
        System.out.println("Running testCalcShortestPath_DirectEdge...");
        String result = app.calcShortestPath(graph, "a", "b");
        try {
            assertEquals("a -> b", result);
            System.out.println("testCalcShortestPath_DirectEdge: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_DirectEdge: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_MultiplePaths() {
        System.out.println("Running testCalcShortestPath_MultiplePaths...");
        String result = app.calcShortestPath(graph, "a", "c");
        try {
            assertTrue(result.equals("a -> b -> c") || result.equals("a -> d -> e -> c"));
            System.out.println("testCalcShortestPath_MultiplePaths: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_MultiplePaths: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_Unreachable() {
        System.out.println("Running testCalcShortestPath_Unreachable...");
        graph.addVertex("isolated");
        String result = app.calcShortestPath(graph, "a", "isolated");
        try {
            assertEquals("从 a 到 isolated 不可达。", result);
            System.out.println("testCalcShortestPath_Unreachable: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_Unreachable: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_Word1NotExist() {
        System.out.println("Running testCalcShortestPath_Word1NotExist...");
        String result = app.calcShortestPath(graph, "x", "a");
        try {
            assertEquals("No \"x\" in the graph!", result);
            System.out.println("testCalcShortestPath_Word1NotExist: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_Word1NotExist: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_Word2NotExist() {
        System.out.println("Running testCalcShortestPath_Word2NotExist...");
        String result = app.calcShortestPath(graph, "a", "z");
        try {
            assertEquals("No \"z\" in the graph!", result);
            System.out.println("testCalcShortestPath_Word2NotExist: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_Word2NotExist: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_BothWordsNotExist() {
        System.out.println("Running testCalcShortestPath_BothWordsNotExist...");
        String result = app.calcShortestPath(graph, "x", "z");
        try {
            assertEquals("No \"x\" in the graph!", result);
            System.out.println("testCalcShortestPath_BothWordsNotExist: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_BothWordsNotExist: 失败 - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testCalcShortestPath_EmptyGraph() {
        System.out.println("Running testCalcShortestPath_EmptyGraph...");
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> emptyGraph =
                new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        String result = app.calcShortestPath(emptyGraph, "a", "b");
        try {
            assertEquals("No \"a\" in the graph!", result);
            System.out.println("testCalcShortestPath_EmptyGraph: 通过");
        } catch (AssertionError e) {
            System.out.println("testCalcShortestPath_EmptyGraph: 失败 - " + e.getMessage());
            throw e;
        }
    }
}