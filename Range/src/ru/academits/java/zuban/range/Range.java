package ru.academits.java.zuban.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{" + from + ", " + to + '}';
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number <= to && number >= from;
    }

    public Range findIntersection(Range range) {
        if (range.to < from || range.from > to) {
            return null;
        }

        double start = Math.min(from, range.from);
        double end = Math.max(to, range.to);

        return new Range(start, end);
    }

    public Range[] createUnion(Range range) {
        if (range.to < from || range.from > to) {
            return new Range[]{new Range(from, to)};
        }

        double start = Math.min(from, range.from);
        double end = Math.max(to, range.to);

        return new Range[]{new Range(start, end)};
    }

    public Range[] calculateDifference(Range range) {
        if (range.from > to || range.to < from) {
            return null;
        } else if (range.from >= from && range.to <= to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        } else if (range.from <= from && range.to >= to) {
            return new Range[]{new Range(range.from, from), new Range(to, range.to)};
        } else if (range.from < from && range.to > from) {
            return new Range[]{new Range(from, range.to)};
        } else if (range.from > from && range.from < to) {
            return new Range[]{new Range(range.from, to)};
        }

        return null;
    }
}
