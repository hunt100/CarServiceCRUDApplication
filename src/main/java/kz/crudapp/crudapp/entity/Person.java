package kz.crudapp.crudapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotBlank(message = "patronymic is mandatory")
    private String patronymic;

    public Person() {
    }

    public Person(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
