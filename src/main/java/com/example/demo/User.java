package com.example.demo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQuery(query = "Select u from User u where u.id =:id", name = "findUserById1")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
