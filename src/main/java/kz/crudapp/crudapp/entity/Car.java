package kz.crudapp.crudapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private String yearOfIssue;

    private Long mileage;

    @OneToOne
    private Owner owner;

    @OneToMany(mappedBy = "car")
    @JsonIgnoreProperties("car")
    private List<Work> work;

    public Car() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }
}
