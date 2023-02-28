package shoppinglist.models;

import shoppinglist.constants.Payment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "footwear_id"))
    private List<Footwear>footwears;

    @Size(min=1, max = 45)
    @Column(length = 45,nullable = false)
    private String city;

    private int fee;
    @Size(min=1, max = 45)
    @Column(length = 45,nullable = false)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private double total;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double totalAmount) {
        this.total = totalAmount;
    }


    public List<Footwear> getFootwears() {
        return footwears;
    }

    public void setFootwears(List<Footwear> footwears) {
        this.footwears = footwears;
    }
}
