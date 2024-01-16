package com.backend.orbitr.dal.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SpaceshipId", nullable = false)
    private Spaceship spaceship;

    @Column(name = "Price", nullable = false)
    private int price;

    public Offer(){
    }

    public Offer(Spaceship spaceship, int price) {
        this.spaceship = spaceship;
        this.price = price;
    }

    public Offer(User user, Spaceship spaceship, int price) {
        this.user = user;
        this.spaceship = spaceship;
        this.price = price;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
