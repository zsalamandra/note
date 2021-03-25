package ru.personwall.note.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;


    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REFRESH)
    private Set<User> users;

    public Role(){}

    public Role(Long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.users = Set.of(owner);
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
       return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
