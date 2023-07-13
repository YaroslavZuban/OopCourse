package ru.academits.java.zuban.main;

import ru.academits.java.zuban.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3, 9);
        Range range2 = new Range(5, 12);

        Range result = range1.findIntersection(range2);
        System.out.println("intersections " + result);

        Range[] union = range1.createUnion(range2);
        System.out.println("union " + Arrays.toString(union));

        Range[] difference = range1.calculateDifference(range2);
        System.out.println("difference " + Arrays.toString(difference));

        range1 = new Range(-2, 3);
        range2 = new Range(4, 10);

        result = range1.findIntersection(range2);
        System.out.println("intersections " + result);

        union = range1.createUnion(range2);
        System.out.println("union " + Arrays.toString(union));

        difference = range1.calculateDifference(range2);
        System.out.println("difference " + Arrays.toString(difference));
    }
}
