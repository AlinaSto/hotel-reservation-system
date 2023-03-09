package spring.bookingApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.dto.RegisterDTO;
import spring.bookingApp.model.User;
import spring.bookingApp.model.Role;
import spring.bookingApp.model.RoleType;
import spring.bookingApp.repository.RoleRepository;
import spring.bookingApp.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
@Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    public User register(RegisterDTO newUser) {
        Optional<User> foundUserOptional = userRepository.findUserByUsername(newUser.getUsername());
        if (foundUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CREATED, "already exist");
        }
        User user = new User();
        user.setUsername(newUser.getUsername());

        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role foundRole = roleRepository.findByRoleType(newUser.getRoleType());
        if (foundRole != null) {
            setRoleOfUser(user, foundRole);
        } else if (newUser.getRoleType().equals(RoleType.ROLE_ADMIN) || newUser.getRoleType().equals(RoleType.ROLE_CLIENT)) {
            Role newRole = roleService.addRole(newUser.getRoleType());
            setRoleOfUser(user, newRole);
        }
        return userRepository.save(user);

    }
    private static void setRoleOfUser(User user, Role role) {
        user.getRoleList().add(role);
        role.getUserList().add(user);
    }
    public User findLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return foundUser;
    }
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the user was not found"));
    }
}