package com.company;

public class QuantityDiscount extends BaseDecorator {
    public QuantityDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Product getDiscount() {
        Product product = super.getDiscount();
        if(product.getQuantity() > 10) {
            System.out.println("More than 10 items, 5 dollard discount: ");
            System.out.println("Old price: " + product.getPrice());
            product.setPrice(product.getPrice()-5);
            System.out.println("New price: " + product.getPrice());
        }
        return product;
    }
}
