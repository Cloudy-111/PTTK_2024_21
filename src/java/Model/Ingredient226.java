/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Tuan Diep
 */
public class Ingredient226 {
    private int id;
    private String name;
    private String description;
    private double price;
    private int remain;

    public Ingredient226(int id, String name, String description, double price, int remain) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.remain = remain;
    }

    public Ingredient226() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
    
    
}
