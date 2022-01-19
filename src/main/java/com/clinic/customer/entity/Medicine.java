package com.clinic.customer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private float price;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.MERGE)
    private List<Medicine_img> imgs = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "medicineType_id", nullable = false)
    private Medicine_type medicine_type;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.MERGE)
    private List<CheckupDetail> checkupDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Medicine_img> getImgs() {
        return imgs;
    }

    public void setImgs(List<Medicine_img> imgs) {
        this.imgs = imgs;
    }

    public Medicine_type getMedicine_type() {
        return medicine_type;
    }

    public void setMedicine_type(Medicine_type medicine_type) {
        this.medicine_type = medicine_type;
    }

    public List<CheckupDetail> getCheckupDetails() {
        return checkupDetails;
    }

    public void setCheckupDetails(List<CheckupDetail> checkupDetails) {
        this.checkupDetails = checkupDetails;
    }
}
