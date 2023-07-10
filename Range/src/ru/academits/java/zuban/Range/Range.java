package ru.academits.java.zuban.Range;

public class Range {
    @Override
    public String toString() {
        return "{" + from +
                "," + to +
                '}';
    }

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double length() {
        return this.to - this.from;
    }

    public boolean isInside(double number) {
        return this.to >= number && this.from <= number;
    }

    public Range intersections(Range newRange) {
        if (newRange.getFrom() <= this.from && newRange.getTo() <= this.to && this.from <= newRange.to) {
            return new Range(newRange.getFrom(), this.to);
        } else if (this.from <= newRange.getFrom() && newRange.getFrom() <= this.to && this.to <= newRange.getTo()) {
            return new Range(this.from, newRange.getTo());
        } else if (newRange.getFrom() <= this.from && this.to <= newRange.getTo()) {
            return new Range(newRange.getFrom(), newRange.getTo());
        } else if (this.from <= newRange.getFrom() && newRange.getTo() <= this.to) {
            return new Range(this.from, this.to);
        }

        return null;
    }

    public Range[] associations(Range newRange) {
        Range[] array;

        if (newRange.getFrom() <= this.from && newRange.getTo() <= this.to && this.from <= newRange.to) {
            array = new Range[]{new Range(newRange.getFrom(), this.to)};
        } else if (this.from <= newRange.getFrom() && newRange.getFrom() <= this.to && this.to <= newRange.getTo()) {
            array = new Range[]{new Range(this.from, newRange.getTo())};
        } else if (newRange.getFrom() <= this.from && this.to <= newRange.getTo()) {
            array = new Range[]{new Range(newRange.getFrom(), newRange.getTo())};
        } else {
            array = new Range[]{new Range(this.from, this.to)};
        }

        return array;
    }

    public Range[] difference(Range newRange) {
        Range[] array;

        if (newRange.getFrom() <= this.from && newRange.getTo() <= this.to && this.from < newRange.to) {
            array = intervalCheck(newRange, this);
        } else if (this.from <= newRange.getFrom() && newRange.getFrom() < this.to && this.to <= newRange.getTo()) {
            array = intervalCheck(this, newRange);
        } else {
            array = new Range[]{this, newRange};
        }

        return array;
    }

    private Range[] intervalCheck(Range oneRange, Range twoRange) {
        Range[] array;

        if (oneRange.getFrom() == twoRange.getFrom() && oneRange.getTo() == twoRange.getTo()) {
            array = new Range[0];
        } else if (oneRange.getFrom() == twoRange.getFrom() && oneRange.getTo() != twoRange.getTo()) {
            array = new Range[]{new Range(oneRange.getTo(), twoRange.getTo())};
        } else if (oneRange.getFrom() != twoRange.getFrom() && oneRange.getTo() == twoRange.getTo()) {
            array = new Range[]{new Range(oneRange.getFrom(), twoRange.getFrom())};
        } else {
            array = new Range[]{new Range(oneRange.getFrom(), twoRange.getFrom()),
                    new Range(oneRange.getTo(), twoRange.getTo())};
        }

        return array;
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
}
