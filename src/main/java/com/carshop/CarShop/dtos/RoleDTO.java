package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private String name;

    public RoleDTO(String name)
    {        this.name = name;
    }

    public RoleDTO(Role role){
        this.id = role.getRole_id();
        this.name = role.getName();
    }


}
