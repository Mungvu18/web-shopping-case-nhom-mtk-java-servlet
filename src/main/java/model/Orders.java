package model;

import java.util.Date;

public class Orders {
    private int id;
    private int id_account;
    private int status;
    private Date date;

    public Orders(int id, int id_account, int status, Date date) {
        this.id = id;
        this.id_account = id_account;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
