package com.backend.orbitr.dal.models;

import com.backend.orbitr.dal.repositories.TechnologyRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "Spaceships")
public class Spaceship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Model", nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TechnologyId")
    private Technology technology;

    @Column(name = "Description")
    private String description;

    @Column(name = "Picture")
    private String picture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId")
    private User user;

    @Transient
    private int technologyId;

    public Spaceship(){
    }

    public Spaceship(String name, String model, int technologyId, String description, String picture) {
        this.name = name;
        this.model = model;
        this.technologyId = technologyId;
        this.description = description;
        this.picture = picture;
    }

    public Spaceship(String name, String model, Technology technology, String description, String picture, User user) {
        this.name = name;
        this.model = model;
        this.technology = technology;
        this.description = description;
        this.picture = picture;
        this.user = user;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }
}
