package shoppinglist.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "footwears")
public class Footwear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "footwears")
    private List<Order>orders;

    @NotNull
    @Column(length = 60, nullable = false)
    private String brand;
    @NotNull
    @Column(length = 60, nullable = false)
    private String model;
    @Min(1)
    @Column(length = 60, nullable = false)
    private int size;
    @Column(length = 20, nullable = false)
    private String color;
    @Min(1)
    @Column(length = 4)
    private Double price;
    @Min(1)
    private int quantity;

    public Footwear() {
    }

    public Footwear(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return this.brand + " | " + this.color + " | " + this.price;
    }
}
