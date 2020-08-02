package com.pzombade.shoppingcart;

import java.util.Objects;

public class DiscountSlab {

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

    public DiscountSlab(){}

    public DiscountSlab(long min, long max, long discountPercentage, boolean isLastSlab) {
        this.min = min;
        this.max = max;
        this.isLastSlab = isLastSlab;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountSlab that = (DiscountSlab) o;
        return min == that.min &&
                max == that.max &&
                discountPercentage == that.discountPercentage &&
                isLastSlab == that.isLastSlab;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, discountPercentage, isLastSlab);
    }

    @Override
    public String toString() {
        return "DiscountRow{" +
                "min=" + min +
                ", max=" + max +
                ", discountPercentage=" + discountPercentage +
                ", isLastSlab=" + isLastSlab +
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
