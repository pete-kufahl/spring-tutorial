package net.prk.base_domains.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public void setOrderId() {
        setOrderId(null);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;
    private String name;
    private int qty;
    private double price;

    public String getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
