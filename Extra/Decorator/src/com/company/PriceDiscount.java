package com.company;

public class PriceDiscount extends BaseDecorator {
    public PriceDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Product getDiscount() {
        Product product = super.getDiscount();
        if(product.getPrice() > 100) {
            System.out.println("100 dollars+, 10 dollars discount:");
            System.out.println("Old price: " + product.getPrice());
            product.setPrice(product.getPrice()-10);
            System.out.println("New price: " + product.getPrice());
        }
        return product;
    }
}
