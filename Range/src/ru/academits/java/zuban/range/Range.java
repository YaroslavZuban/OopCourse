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

    public Range getIntersection(Range range) {
        double maxFrom = Math.max(this.from, range.from);
        double minTo = Math.min(this.to, range.to);

        // Если новый интервал не существует (пересечения нет), вернем null
        if (maxFrom > minTo) {
            return null;
        }

        return new Range(maxFrom, minTo);

    }

    public Range[] getUnion(Range range) {
        if (range.to < from || range.from > to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (range.getFrom() >= to || range.getTo() <= from) {
            return new Range[]{new Range(from, to)};
        } else if (range.getFrom() > from && range.getTo() < to) {
            return new Range[]{new Range(from, range.getFrom()), new Range(range.getTo(), to)};
        } else if (range.getFrom() <= from && range.getTo() >= to) {
            return new Range[0];
        } else if (range.getFrom() < from && range.getTo() > from) {
            return new Range[]{new Range(from, range.getTo())};
        } else if (range.getFrom() > from && range.getFrom() < to) {
            return new Range[]{new Range(range.getFrom(), to)};
        }

        return new Range[0];
    }
}
