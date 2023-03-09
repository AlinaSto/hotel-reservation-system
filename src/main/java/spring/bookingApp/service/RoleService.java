package spring.bookingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.bookingApp.model.Role;
import spring.bookingApp.model.RoleType;
import spring.bookingApp.repository.RoleRepository;

import java.util.ArrayList;
@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(RoleType roleType) {
        Role newRole = new Role();
        newRole.setRoleType(roleType);
        newRole.setUserList(new ArrayList<>());
        return roleRepository.save(newRole);
    }
}
