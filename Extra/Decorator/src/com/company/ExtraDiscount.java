package com.company;

package com.company;

public class QuantityDiscount extends BaseDecorator {
    public QuantityDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public int getPrice() {
        int nowPrice = super.getPrice();
        if(super. > 100) {
            System.out.println("Price > 100, 10 dollars discount:");
            System.out.println("Old price: " + nowPrice);
            nowPrice -= 10;
            System.out.println("New price: " + nowPrice);
        }
        return nowPrice;
    }
}
