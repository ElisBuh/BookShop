package com.senla.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "roles")
public class Role extends AEntity implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "role_id", sequenceName = "role_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id")
    private Integer id;

    @Column(name = "name")
    private String nameRole;
}
