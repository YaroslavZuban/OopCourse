package ru.academits.java.zuban.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void bypassInWidth() {
        List<Integer> output1 = new ArrayList<>();
        List<Integer> output2 = new ArrayList<>();

        IntConsumer consumer1 = output1::add;

        Graph graph1 = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}
        });

        graph1.bypassInWidth(0, consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(6);
        output2.add(3);
        output2.add(5);
        output2.add(4);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0},

        });

        graph2.bypassInWidth(0, consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.bypassInWidth(0, consumer3);

        output2.add(0);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.bypassInWidth(0, consumer4);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.bypassInWidth(0, consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.bypassInWidth(0, consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer7 = output1::add;

        Graph graph7 = new Graph(new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph7.bypassInWidth(0, consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(4);
        output2.add(2);
        output2.add(3);
        output2.add(5);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer8 = output1::add;

        Graph graph8 = new Graph(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        graph8.bypassInWidth(0, consumer8);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        assertEquals(output1, output2);

        IntConsumer consumer9 = output1::add;

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph9.bypassInWidth(0, consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);
        output2.add(5);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();
    }

    @org.junit.jupiter.api.Test
    void bypassInDepth() {
        List<Integer> output1 = new ArrayList<>();
        List<Integer> output2 = new ArrayList<>();

        IntConsumer consumer1 = output1::add;

        Graph graph1 = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}
        });

        graph1.bypassInDepth(0, consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);
        output2.add(6);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0},

        });

        graph2.bypassInDepth(0, consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.bypassInDepth(0, consumer3);

        output2.add(0);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.bypassInDepth(0, consumer4);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.bypassInDepth(0, consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.bypassInDepth(0, consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer7 = output1::add;

        Graph graph7 = new Graph(new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph7.bypassInDepth(0, consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer8 = output1::add;

        Graph graph8 = new Graph(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        graph8.bypassInDepth(0, consumer8);
        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer9 = output1::add;

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph9.bypassInDepth(0, consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(4);
        output2.add(3);
        output2.add(5);

        assertEquals(output1, output2);
    }

    @org.junit.jupiter.api.Test
    void bypassInDepthRecursion() {
        List<Integer> output1 = new ArrayList<>();
        List<Integer> output2 = new ArrayList<>();

        IntConsumer consumer1 = output1::add;

        Graph graph1 = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}
        });

        graph1.bypassInDepthRecursion(0, consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);
        output2.add(6);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0},

        });

        graph2.bypassInDepthRecursion(0, consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.bypassInDepthRecursion(0, consumer3);

        output2.add(0);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.bypassInDepthRecursion(0, consumer4);


        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.bypassInDepthRecursion(0, consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.bypassInDepthRecursion(0, consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer7 = output1::add;

        Graph graph7 = new Graph(new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph7.bypassInDepthRecursion(0, consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer8 = output1::add;

        Graph graph8 = new Graph(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        graph8.bypassInDepthRecursion(0, consumer8);
        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);

        assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer9 = output1::add;

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph9.bypassInDepthRecursion(0, consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(4);
        output2.add(3);
        output2.add(5);

        assertEquals(output1, output2);
    }
}