package kz.crudapp.crudapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    @JsonIgnoreProperties("work")
    private Worker worker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    @JsonIgnoreProperties("work")
    private Car car;

    private String workDone;

    public Work() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }
}
