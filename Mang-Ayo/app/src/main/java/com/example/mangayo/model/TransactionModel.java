package com.example.mangayo.model;

public class TransactionModel {
    private String transaction_id,date_service,service_type,service_cost,payment_type,user_id,mechanic_id;

    public TransactionModel(String transaction_id, String date_service, String service_type, String payment_type, String service_cost) {
        this.transaction_id = transaction_id;
        this.date_service = date_service;
        this.service_type = service_type;
        this.service_cost = service_cost;
        this.payment_type = payment_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getDate_service() {
        return date_service;
    }

    public void setDate_service(String date_service) {
        this.date_service = date_service;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_cost() {
        return service_cost;
    }

    public void setService_cost(String service_cost) {
        this.service_cost = service_cost;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(String mechanic_id) {
        this.mechanic_id = mechanic_id;
    }
}
