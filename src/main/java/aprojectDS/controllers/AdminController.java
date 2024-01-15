package aprojectDS.controllers;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import aprojectDS.entities.Role;
import aprojectDS.entities.User;
import aprojectDS.payload.request.AddUserRequest;
import aprojectDS.payload.request.UpdateUserRequest;
import aprojectDS.payload.response.MessageResponse;
import aprojectDS.services.UserService;


import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Operation(summary = "Add a new user", description = "Adds a new user in the admin page")
    @PostMapping("/addUser")
    public ResponseEntity<MessageResponse> addUser(@RequestBody AddUserRequest addUserRequest) {
            List<String> strRoles = addUserRequest.getRole();
            User user = new User(addUserRequest.getUsername(),
                    addUserRequest.getEmail(),
                    encoder.encode(addUserRequest.getPassword()), addUserRequest.getFirstName(), addUserRequest.getLastName(), true);
            List<Role> roles = userService.createRoles(strRoles);
            user.setRoles(roles);
        try {
            userService.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @Operation(summary = "Update a user", description = "Updates a user in the admin page")
    @PutMapping("/updateUser")
    public ResponseEntity<MessageResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {

        String email = updateUserRequest.getEmail();
        userService.checkEmail(email);
        String username = updateUserRequest.getUsername();
        userService.checkUsername(username);
        User updateUser = userService.getUserById(updateUserRequest.getId());
        updateUser.setUsername(updateUserRequest.getUsername());
        updateUser.setEmail(updateUserRequest.getEmail());
        updateUser.setFirstName(updateUserRequest.getFirstName());
        updateUser.setLastName(updateUserRequest.getLastName());
        List<Role> roles = updateUser.getRoles();
        updateUser.setRoles(roles);
        userService.updateUser(updateUser);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @Operation(summary = "Delete a user", description = "Deletes a user in the admin page")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<MessageResponse> disableOrEnableUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully! " + id));
    }

    @Operation(summary = "Find all users", description = "Finds all users in the admin page")
    @GetMapping("/findAll")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }



    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
