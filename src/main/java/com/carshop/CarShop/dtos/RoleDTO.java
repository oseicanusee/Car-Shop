package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Enum_Role;
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
    private Enum_Role name;

    public RoleDTO(Enum_Role name)
    {        this.name = name;
    }

    public RoleDTO(Role role){
        this.id = role.getRole_id();
        this.name = role.getName();
    }


}
