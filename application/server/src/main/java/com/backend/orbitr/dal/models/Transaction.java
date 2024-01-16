package com.backend.orbitr.dal.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "UserID")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SpaceshipID")
    private Spaceship spaceship;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private int price;

    @Column(name = "TransactionDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String transactionDate;

    public Transaction(){
    }

    public Transaction(int userId, Spaceship spaceship, String transactionType, String description, int price, String transactionDate) {
        this.userId = userId;
        this.spaceship = spaceship;
        this.transactionType = transactionType;
        this.description = description;
        this.price = price;
        this.transactionDate = transactionDate;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}