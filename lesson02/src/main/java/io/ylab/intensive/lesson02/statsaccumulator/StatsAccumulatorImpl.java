package io.ylab.intensive.lesson02.statsaccumulator;

public class StatsAccumulatorImpl implements StatsAccumulator {
    private int min;
    private int max;
    private int sum;
    private int count;

    @Override
    public void add(int value) {
        if (count > 0) {
            if (value < this.min) {
                this.min = value;
            } else if (value > max) {
                this.max = value;
            }
        } else {
            this.min = value;
            this.max = value;
        }
        this.sum += value;
        this.count++;
    }

    @Override
    public int getMin() {
        return this.min;
    }

    @Override
    public int getMax() {
        return this.max;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Double getAvg() {
        return (double) sum / count;
    }
}
