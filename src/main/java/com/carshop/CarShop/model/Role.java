package com.carshop.CarShop.model;


import com.carshop.CarShop.dtos.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long role_id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Enum_Role name;

    public Role(Enum_Role name){
        this.name = name;
    }

    public Role(RoleDTO roleDTO){
        this.name = roleDTO.getName();
    }
}
