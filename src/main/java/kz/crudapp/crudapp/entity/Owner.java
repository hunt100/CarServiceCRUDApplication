package kz.crudapp.crudapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner extends Person{

    private String telephone;

    private String address;

    public Owner() {
    }

    public Owner(String firstName, String lastName, String patronymic, String telephone, String address) {
        super(firstName, lastName, patronymic);
        this.telephone = telephone;
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
