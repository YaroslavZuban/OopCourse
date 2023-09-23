package ru.academits.java.zuban.graph.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.academits.java.zuban.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

class GraphTest {
    @Test
    protected void bypassInWidth() {
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

        graph1.bypassInWidth(consumer1);

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

        graph2.bypassInWidth(consumer2);

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

        graph3.bypassInWidth(consumer3);

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

        graph4.bypassInWidth(consumer4);

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

        graph5.bypassInWidth(consumer5);

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

        graph6.bypassInWidth(consumer6);

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

        graph7.bypassInWidth(consumer7);

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

        graph8.bypassInWidth(consumer8);

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

        graph9.bypassInWidth(consumer9);

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
    protected void bypassInDepth() {
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

        graph1.bypassInDepth(consumer1);

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

        graph2.bypassInDepth(consumer2);

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

        graph3.bypassInDepth(consumer3);

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

        graph4.bypassInDepth(consumer4);

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

        graph5.bypassInDepth(consumer5);

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

        graph6.bypassInDepth(consumer6);

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

        graph7.bypassInDepth(consumer7);

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

        graph8.bypassInDepth(consumer8);
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

        graph9.bypassInDepth(consumer9);

        output2.add(0);
        output2.add(1);
        output2.add(2);
        output2.add(4);
        output2.add(3);
        output2.add(5);

        Assertions.assertEquals(output1, output2);
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
}