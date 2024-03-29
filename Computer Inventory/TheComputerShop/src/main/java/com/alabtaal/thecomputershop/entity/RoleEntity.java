package com.alabtaal.thecomputershop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(schema = "inventory" , name = "ROLES")
public class RoleEntity extends Auditable<String> {


    @Id
    @Column(name = "ROLE_ID")
    private UUID roleId;

    @Column(name = "NAME")
    private String name;
}
