package com.carshop.CarShop.Service.Impl;

import com.carshop.CarShop.Service.RoleService;
import com.carshop.CarShop.configuration.MapStructMapper;
import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.exceptions.ResourceNotFoundException;
import com.carshop.CarShop.model.Role;
import com.carshop.CarShop.repository.RoleRepository;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isPresent()){
            Role role = roleOptional.get();
            RoleDTO roleDTO = mapStructMapper.roleToRoleDTO(role);
            return roleDTO;
        }
        throw new ResourceNotFoundException("Role", "id", id);
    }

    @Override
    public RoleDTO findByName(String name) {
        Optional<Role> roleOptional = roleRepository.findByName(name);

        if(roleOptional.isPresent()){
            Role role = roleOptional.get();
            return mapStructMapper.roleToRoleDTO(role);
        }

        throw new ResourceNotFoundException("Role name not found");
    }

    @Override
    public Role saveOrUpdate(RoleDTO roleDTO) {
        return roleRepository.saveAndFlush(mapStructMapper.roleDTOToRole(roleDTO));
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        roleRepository.deleteById(id);
        jsonObject.put("message", "Role deleted successfully");
        return jsonObject.toString();
    }

}
