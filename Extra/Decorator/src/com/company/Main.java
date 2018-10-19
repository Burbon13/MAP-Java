package com.company;

public class Main {

    public static void main(String[] args) {
        Discount discount = new FruitsDiscount(new QuantityDiscount(new PriceDiscount(new StrategyDiscount(new Product(1234,20,"Bananas")))));

        System.out.println(discount.getDiscount().getPrice());
    }
}
