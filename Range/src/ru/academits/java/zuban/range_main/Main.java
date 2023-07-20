package ru.academits.java.zuban.range_main;

import ru.academits.java.zuban.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3, 9);
        Range range2 = new Range(5, 1);

        print(range1, range2);

        range1 = new Range(-2, 3);
        range2 = new Range(4, 10);

        print(range1, range2);
    }

    public static void print(Range range1, Range range2) {
        Range result = range1.getIntersection(range2);
        System.out.println("Intersections: " + result);

        Range[] union = range1.getUnion(range2);
        System.out.println("Union: " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Difference: " + Arrays.toString(difference));
    }
}
