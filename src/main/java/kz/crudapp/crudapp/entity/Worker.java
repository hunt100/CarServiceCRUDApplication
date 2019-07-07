package kz.crudapp.crudapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Worker extends Person{
    @OneToMany(mappedBy = "worker")
    @JsonIgnoreProperties("worker")
    private List<Work> work;

    public Worker() {
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }
}
