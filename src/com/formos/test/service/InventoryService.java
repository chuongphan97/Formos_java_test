package com.formos.test.service;

import com.formos.test.enums.Product;

public interface InventoryService {
    void listCurrentInventory();

    void sellProduct(Product name, Integer quantity);

    void dailySales();

}
