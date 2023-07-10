package ru.academits.java.zuban.Range;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range one = new Range(3, 9);
        Range two = new Range(5, 10);

        Range result = one.intersections(two);
        System.out.println(result);

        Range[] resultArray = one.associations(two);
        System.out.println(Arrays.toString(resultArray));

        resultArray = one.difference(two);
        System.out.println(Arrays.toString(resultArray));

        one = new Range(-2, 3);
        two = new Range(4, 10);

        result = one.intersections(two);
        System.out.println(result);

        resultArray = one.associations(two);
        System.out.println(Arrays.toString(resultArray));

        resultArray = one.difference(two);
        System.out.println(Arrays.toString(resultArray));
    }
}
