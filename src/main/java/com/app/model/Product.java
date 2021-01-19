package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

    @Id
    @Column(name="ID")
    private int ID;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Brand")
    private String Brand;

    @Column(name = "Price")
    private String Price;

    @Column(name = "categID")
    private String categID;

    public Product(){

    }

    public Product(int itemID, String itemName, String itemDescription, String itemBrand, String itemPrice, String itemSpecifications) {
        this.ID = itemID;
        this.Name = itemName;
        this.Description = itemDescription;
        this.Brand = itemBrand;
        this.Price = itemPrice;
        this.categID = itemSpecifications;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getCategID() {
        return categID;
    }

    public void setCategID(String categID) {
        this.categID = categID;
    }
}
