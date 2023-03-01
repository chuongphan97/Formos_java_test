package com.formos.test.service;

import com.formos.test.constant.Constants;
import com.formos.test.enums.IngredientName;
import com.formos.test.enums.Product;
import com.formos.test.model.Ingredient;
import com.formos.test.model.SaleReport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private static final List<Ingredient> ingredients;
    private static final List<SaleReport> saleReports;

    static {
        ingredients = new ArrayList<>();
        saleReports = new ArrayList<>();

        //Hard code vendor's inventory of ingredients
        for (IngredientName ingredientName :
                IngredientName.values()) {
            Ingredient ingredient = new Ingredient(ingredientName, Constants.DEFAULT_INGREDIENT_AMOUNT);
            ingredient.setId(ingredients.size() + 1);
            ingredients.add(ingredient);
        }
    }

    @Override
    public void listCurrentInventory() {
        ingredients.forEach(ingredient -> {
            System.out.println(ingredient.toString());
        });
    }

    @Override
    public void sellProduct(Product product, Integer quantity) {
        if (validateIngredientsForSales(product, quantity)) {
            reduceIngredientsInventory(product, quantity);
            updateSaleReport(product, quantity);

        }
    }

    @Override
    public void dailySales() {
        LocalDateTime now = LocalDateTime.now();
        double revenue = 0D;
        double cost = 0D;
        for (SaleReport saleReport :
                saleReports) {
            if (saleReport.getDateTime().getDayOfYear() == now.getDayOfYear() && saleReport.getDateTime().getYear() == now.getYear()) {
                System.out.println(saleReport.toString());
                revenue = (revenue + saleReport.getTotalPrice());
                cost = cost + saleReport.getCostPrice();
            }
        }
        System.out.println("Daily revenue: " + revenue + ", cost: " + cost + ", profit: " + (revenue - cost));
    }

    private boolean validateIngredientsForSales(Product product, Integer quantity) {
        if (amountIngredientInInventory(IngredientName.ICE) < Constants.REQUIRED_AMOUNT_OF_ICE * quantity) {
            System.out.println(Constants.NOT_ENOUGH_ICE);
            return false;
        }
        if (amountIngredientInInventory(IngredientName.CONDENSED_MILK) < Constants.REQUIRED_AMOUNT_OF_CONDENSED_MILK * quantity) {
            System.out.println(Constants.NOT_ENOUGH_CONDENSED_MILK);
            return false;
        }
        if (amountIngredientInInventory(IngredientName.SUGAR) < Constants.REQUIRED_AMOUNT_OF_SUGAR * quantity) {
            System.out.println(Constants.NOT_ENOUGH_SUGAR);
            return false;
        }

        switch (product) {
            case MANGO:
                if (amountIngredientInInventory(IngredientName.BLENDED_MANGOES) < Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity) {
                    System.out.println(Constants.NOT_ENOUGH_MANGOES_BLENDED);
                    return false;
                }
                break;
            case BANANA:
                if (amountIngredientInInventory(IngredientName.BLENDED_BANANAS) < Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity) {
                    System.out.println(Constants.NOT_ENOUGH_BANANAS_BLENDED);
                    return false;
                }
                break;
            case STRAW_BERRY:
                if (amountIngredientInInventory(IngredientName.BLENDED_STRAWBERRIES) < Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity) {
                    System.out.println(Constants.NOT_ENOUGH_STRAWBERRIES_BLENDED);
                    return false;
                }
                break;
        }
        return true;
    }

    private Integer amountIngredientInInventory(IngredientName ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return null == ingredient.getAmount() ? 0 : ingredient.getAmount();
            }
        }
        return 0;
    }

    private void reduceIngredientsInventory(Product name, Integer quantity) {
        int amountIceLeft = 0;
        int amountSugarLeft = 0;
        int amountCondensedMilkLeft = 0;
        int amountBlendedFruitLeft = 0;

        for (Ingredient ingredient :
                ingredients) {
            if (ingredient.getName().equals(IngredientName.ICE)) {
                amountIceLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_ICE * quantity;
                ingredient.setAmount(amountIceLeft);
            }
            if (ingredient.getName().equals(IngredientName.SUGAR)) {
                amountSugarLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_SUGAR * quantity;
                ingredient.setAmount(amountSugarLeft);
            }
            if (ingredient.getName().equals(IngredientName.CONDENSED_MILK)) {
                amountCondensedMilkLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_CONDENSED_MILK * quantity;
                ingredient.setAmount(amountCondensedMilkLeft);
            }
            if (name.equals(Product.BANANA) && ingredient.getName().equals(IngredientName.BLENDED_BANANAS)) {
                amountBlendedFruitLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity;
                ingredient.setAmount(amountBlendedFruitLeft);
            }
            if (name.equals(Product.STRAW_BERRY) && ingredient.getName().equals(IngredientName.BLENDED_STRAWBERRIES)) {
                amountBlendedFruitLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity;
                ingredient.setAmount(amountBlendedFruitLeft);
            }
            if (name.equals(Product.MANGO) && ingredient.getName().equals(IngredientName.BLENDED_MANGOES)) {
                amountBlendedFruitLeft = ingredient.getAmount() - Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * quantity;
                ingredient.setAmount(amountBlendedFruitLeft);
            }
        }
        System.out.println("SOLD " + quantity + " " + name.getName() + "!");

        System.out.println("[Inventory] ICE: " + amountIceLeft +
                "ml, SUGAR: " + amountSugarLeft +
                "g, CONDENSED MILK: " + amountCondensedMilkLeft +
                "ml, " +
                (name.equals(Product.MANGO) ? "BLENDED MANGOES" : (name.equals(Product.BANANA) ? "BLENDED BANANAS" : "BLENDED STRAWBERRIES")) + ": " + amountBlendedFruitLeft +
                "ml");
    }

    private void updateSaleReport(Product product, Integer quantity) {
        saleReports.add(new SaleReport(
                saleReports.size() + 1,
                product,
                quantity,
                product.getPrice() * quantity,
                calculateCost(quantity),
                LocalDateTime.now()
        ));
    }

    private double calculateCost(Integer quantity) {
        double costBlendedFruit = quantity * Constants.REQUIRED_AMOUNT_OF_BLENDED_FRUIT * Constants.COST_OF_100ML_BLENDED_FRUIT / 100;
        double costSugar = quantity * Constants.REQUIRED_AMOUNT_OF_SUGAR * Constants.COST_OF_100G_SUGAR / 100;
        double costIce = quantity * Constants.REQUIRED_AMOUNT_OF_ICE * Constants.COST_OF_1000ML_ICE / 1000;
        double costMilk = quantity * Constants.REQUIRED_AMOUNT_OF_CONDENSED_MILK * Constants.COST_OF_100ML_CONDENSED_MILK / 100;
        return costIce + costSugar + costMilk + costBlendedFruit;
    }
}
