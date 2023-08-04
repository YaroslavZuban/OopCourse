package ru.academits.java.zuban.range_main;

import ru.academits.java.zuban.range.Range;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3, 9);
        Range range2 = new Range(5, 1);

        printOperations(range1, range2);

        range1 = new Range(-2, 3);
        range2 = new Range(4, 10);

        printOperations(range1, range2);

        range1 = new Range(1, 5);
        range2 = new Range(3, 7);

        printOperations(range1, range2);

        range1 = new Range(3, 7);
        range2 = new Range(1, 5);

        printOperations(range1, range2);

        range1 = new Range(1, 5);
        range2 = new Range(1, 3);

        printOperations(range1, range2);

        range1 = new Range(3, 7);
        range2 = new Range(5, 7);

        printOperations(range1, range2);
    }

    public static void printOperations(Range range1, Range range2) {
        System.out.println("First range: " + range1);
        System.out.println("Second range: " + range2);

        Range intersection = range1.getIntersection(range2);
        System.out.println("Intersection: " + intersection);

        Range[] union = range1.getUnion(range2);
        System.out.println("Union: " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Difference: " + Arrays.toString(difference));
        System.out.println();
    }
}