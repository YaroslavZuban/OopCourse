package ru.academits.java.zuban.graph;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void searchWidth() {
        Graph graph1 = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 7, 4, 6, 5}, graph1.searchWidth());


        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0},

        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph2.searchWidth());

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        assertArrayEquals(new Object[]{1}, graph3.searchWidth());

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph4.searchWidth());

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph5.searchWidth());

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 4}, graph6.searchWidth());

        Graph graph7 = new Graph(new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 5, 3, 4, 6}, graph7.searchWidth());

        Graph graph8 = new Graph(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, graph8.searchWidth());

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 4, 3, 5, 6}, graph9.searchWidth());
    }

    @org.junit.jupiter.api.Test
    void searchDepth() {
        Graph graph1 = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7}, graph1.searchDepth());


        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0},

        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph2.searchDepth());

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        assertArrayEquals(new Object[]{1}, graph3.searchDepth());

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph4.searchDepth());

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3}, graph5.searchDepth());

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 4}, graph6.searchDepth());

        Graph graph7 = new Graph(new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6}, graph7.searchDepth());

        Graph graph8 = new Graph(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 4, 3, 5}, graph8.searchDepth());

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        assertArrayEquals(new Object[]{1, 2, 3, 5, 4, 6}, graph9.searchDepth());
    }
}