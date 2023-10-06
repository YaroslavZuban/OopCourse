package ru.academits.java.zuban.graph.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.academits.java.zuban.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

class GraphTest {
    @Test
    protected void traverseInWidth() {
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

        graph1.traverseInWidth(consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(6);
        output2.add(3);
        output2.add(5);
        output2.add(4);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        });

        graph2.traverseInWidth(consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.traverseInWidth(consumer3);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.traverseInWidth(consumer4);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.traverseInWidth(consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.traverseInWidth(consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        Assertions.assertEquals(output1, output2);

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

        graph7.traverseInWidth(consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(4);
        output2.add(2);
        output2.add(3);
        output2.add(5);

        Assertions.assertEquals(output1, output2);

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

        graph8.traverseInWidth(consumer8);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        Assertions.assertEquals(output1, output2);

        IntConsumer consumer9 = output1::add;

        Graph graph9 = new Graph(new int[][]{
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        });

        graph9.traverseInWidth(consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);
        output2.add(5);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();
    }

    @Test
    protected void traverseInDepth() {
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

        graph1.traverseInDepth(consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);
        output2.add(6);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        });

        graph2.traverseInDepth(consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.traverseInDepth(consumer3);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.traverseInDepth(consumer4);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.traverseInDepth(consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.traverseInDepth(consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        Assertions.assertEquals(output1, output2);

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

        graph7.traverseInDepth(consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);

        Assertions.assertEquals(output1, output2);

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

        graph8.traverseInDepth(consumer8);
        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);

        Assertions.assertEquals(output1, output2);

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

        graph9.traverseInDepth(consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(4);
        output2.add(3);
        output2.add(5);

        Assertions.assertEquals(output1, output2);
    }

    @Test
    protected void traverseInDepthRecursive() {
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

        graph1.traverseInDepthRecursive(consumer1);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);
        output2.add(6);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer2 = output1::add;

        Graph graph2 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        });

        graph2.traverseInDepthRecursive(consumer2);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer3 = output1::add;

        Graph graph3 = new Graph(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        graph3.traverseInDepthRecursive(consumer3);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer4 = output1::add;

        Graph graph4 = new Graph(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        graph4.traverseInDepthRecursive(consumer4);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer5 = output1::add;

        Graph graph5 = new Graph(new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        });

        graph5.traverseInDepthRecursive(consumer5);

        output2.add(0);
        output2.add(1);
        output2.add(2);

        Assertions.assertEquals(output1, output2);

        output1.clear();
        output2.clear();

        IntConsumer consumer6 = output1::add;

        Graph graph6 = new Graph(new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        });

        graph6.traverseInDepthRecursive(consumer6);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);

        Assertions.assertEquals(output1, output2);

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

        graph7.traverseInDepthRecursive(consumer7);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(3);
        output2.add(4);
        output2.add(5);

        Assertions.assertEquals(output1, output2);

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

        graph8.traverseInDepthRecursive(consumer8);
        output2.add(0);
        output2.add(1);
        output2.add(3);
        output2.add(2);
        output2.add(4);

        Assertions.assertEquals(output1, output2);

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

        graph9.traverseInDepthRecursive(consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(4);
        output2.add(3);
        output2.add(5);

        Assertions.assertEquals(output1, output2);
    }
}