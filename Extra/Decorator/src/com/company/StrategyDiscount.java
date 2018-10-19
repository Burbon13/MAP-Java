package com.company;

public class StrategyDiscount implements Discount {
    private Product product;

    public StrategyDiscount(Product product) {
        this.product = product;
    }

    @Override
    public Product getDiscount() {
        return product;
    }
}
