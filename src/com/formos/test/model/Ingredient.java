package com.formos.test.model;

import com.formos.test.enums.IngredientName;

public class Ingredient {
    private Integer id;
    private IngredientName name;
    private Integer amount;

    public Ingredient() {
    }

    public Ingredient(IngredientName name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IngredientName getName() {
        return name;
    }

    public void setName(IngredientName name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                (name.equals(IngredientName.SUGAR) ? "g" : "ml") +
                '}';
    }
}
