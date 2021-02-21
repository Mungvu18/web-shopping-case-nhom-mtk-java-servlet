package model;

public class Orderdetail {
    private int id;
    private int id_order;
    private int id_product;
    private int amount;

    public Orderdetail(int id, int id_order, int id_product, int amount) {
        this.id = id;
        this.id_order = id_order;
        this.id_product = id_product;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
