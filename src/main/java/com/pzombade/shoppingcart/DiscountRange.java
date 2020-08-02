package com.pzombade.shoppingcart;

public class DiscountRange {

    private long min;
    private long max;
    private long discountPercentage;

    public boolean isLastSlab() {
        return isLastSlab;
    }

    public void setLastSlab(boolean lastSlab) {
        isLastSlab = lastSlab;
    }

    private boolean isLastSlab = false;

    public DiscountRange(){}

    public DiscountRange(long min, long max, long discountPercentage) {
        this.min = min;
        this.max = max;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "DiscountRow{" +
                "min=" + min +
                ", max=" + max +
                ", discountPercentage=" + discountPercentage +
                '}';
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
