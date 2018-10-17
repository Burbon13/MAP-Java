package com.company;

public class FruitsDiscount extends BaseDecorator {
    public FruitsDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Product getDiscount() {
        Product product = super.getDiscount();
        if(product.getName().equals("Bananas") || product.getName().equals("Apples")) {
            System.out.println("Fruits discount 10%: ");
            System.out.println("Old price: " + product.getPrice());
            product.setPrice((product.getPrice()*9)/10);
            System.out.println("New price: " + product.getPrice());
        }
        return product;
    }
}
