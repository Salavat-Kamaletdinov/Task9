package ru.itmentor.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    public Role() {
    }



    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
