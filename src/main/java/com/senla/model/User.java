package com.senla.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQueries(
        {
                @NamedQuery(
                        name = "findByUserLogin",
                        query = "from User as s where s.login = :login"
                )
        }
)

@Data
@Entity
@Table(name = "users")
public class User extends AEntity implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id", sequenceName = "user_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "passwords")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
