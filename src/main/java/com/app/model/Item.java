package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Items")
public class Item implements Serializable {

    @Id
    @Column(name="ItemID")
    private int itemID;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "ItemDescription")
    private String itemDescription;

    @Column(name = "ItemBrand")
    private String itemBrand;

    @Column(name = "ItemPrice")
    private String itemPrice;

    @Column(name = "ItemSpecifications")
    private String itemSpecifications;

    public Item(){

    }

    public Item(int itemID, String itemName, String itemDescription, String itemBrand, String itemPrice, String itemSpecifications) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemBrand = itemBrand;
        this.itemPrice = itemPrice;
        this.itemSpecifications = itemSpecifications;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSpecifications() {
        return itemSpecifications;
    }

    public void setItemSpecifications(String itemSpecifications) {
        this.itemSpecifications = itemSpecifications;
    }
}
