package com.example.quintoimpacto.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @JoinColumn(name = "user_id")
    private Long id;
    private String ssn; /* SOCIAL SECURITY NUMBER */
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String birthday;
    private String state;
    private String city;
    private String address;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Inscription> inscriptions = new HashSet<>();
    public User() {
    }
    public User(String ssn, String name, String lastName, String email, String phone, String birthday, String state, String city, String address, String password) {
        this.ssn = ssn;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.state = state;
        this.city = city;
        this.address = address;
        this.password = password;
    }
    public void addInscription(Inscription inscription){
        inscription.setUser(this);
        inscriptions.add(inscription);
    }

    /* GETTERS */
    public Long getId() {return id;}
    public String getSsn() {return ssn;}
    public String getName() {return name;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public String getBirthday() {return birthday;}
    public String getState() {return state;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public String getPassword() {return password;}
    public Set<Inscription> getInscriptions() {return inscriptions;}

    /* SETTERS */
    public void setSsn(String ssn) {this.ssn = ssn;}
    public void setName(String name) {this.name = name;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setBirthday(String birthday) {this.birthday = birthday;}
    public void setState(String state) {this.state = state;}
    public void setCity(String city) {this.city = city;}
    public void setAddress(String address) {this.address = address;}
    public void setPassword(String password) {this.password = password;}
    public void setInscriptions(Set<Inscription> inscriptions) {this.inscriptions = inscriptions;}
}