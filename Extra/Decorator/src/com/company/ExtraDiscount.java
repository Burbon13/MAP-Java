package com.company;

public class ExtraDiscount extends BaseDecorator {
    public ExtraDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Product getDiscount() {
        Product product = super.getDiscount();
        if(product.getPrice() > 1000) {
            System.out.println("100 dollars+, 100 dollars discount:");
            System.out.println("Old price: " + product.getPrice());
            product.setPrice(product.getPrice()-100);
            System.out.println("New price: " + product.getPrice());
        }
        return product;
    }
}
