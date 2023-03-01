package com.formos.test.enums;

public enum Product {
    STRAW_BERRY("Straw berry smoothie", 30000D),
    MANGO("Mango smoothie", 30000D),
    BANANA("Banana smoothie", 30000D);

    private final String name;
    private final Double price;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
