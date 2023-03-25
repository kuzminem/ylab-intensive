package io.ylab.intensive.lesson02.ratelimitedprinter;

public class RateLimitedPrinterImpl implements RateLimitedPrinter {
    private final int interval;
    private long timer;

    RateLimitedPrinterImpl(int interval) {
        this.interval = interval;
        setTimer();
    }

    private void setTimer() {
        this.timer = System.currentTimeMillis() + this.interval;
    }

    @Override
    public void print(String message) {
        if (System.currentTimeMillis() > this.timer) {
            System.out.println(message);
            setTimer();
        }
    }
}
