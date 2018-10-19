package com.company;

public class BaseDecorator implements Discount {
    private Discount discount;

    public BaseDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public Product getDiscount() {
        return discount.getDiscount();
    }
}
