package aprojectDS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aprojectDS.entities.ERole;
import aprojectDS.entities.Role;
import aprojectDS.entities.User;
import aprojectDS.repositories.RoleRepository;
import aprojectDS.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }



    public void checkUsername(String username) {
        userRepository.existsByUsername(username);
    }

    public void checkEmail(String email) {
        userRepository.existsByEmail(email);
    }


    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User createUser(User user) throws Exception {
        userRepository.save(user);
        return user;
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<Role> createRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

    public List<Role> insertUserRole() {
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        return roles;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByFirstNameAndLastName(String firstName, String lastName) {
        List<User> usersByFirstName = userRepository.findByFirstName(firstName);
        for (User u : usersByFirstName) {
            if (u.getLastName().equals(lastName)) {
                return u;
            }
        }
        return null;
    }
}
