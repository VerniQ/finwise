package me.verni.user;

import me.verni.exception.UserLoginException;
import me.verni.exception.UserRegistrationException;
import me.verni.util.EmailValidator;
import me.verni.util.PasswordHasher;
import me.verni.util.PasswordValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CUsernot found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void createUser(String name, String email, String password) {
        if (!EmailValidator.isValidEmail(email)) {
            throw new UserRegistrationException("Invalid email format");
        }

        var validation = PasswordValidator.validatePassword(password);
        if (!validation.isValid()) {
            throw new UserRegistrationException(validation.getMessage());
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserRegistrationException("User with this email already exists");
        }

        if (name.length() <= 3) {
            throw new UserRegistrationException("Name should be at least 4 letters");
        }

        User user = new User(name, email, password, "ROLE_USER");
        userRepository.save(user);
    }


    public User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserLoginException("User with this email does not exist");
        }

        User target = userOptional.get();
        if (!PasswordHasher.matches(password, target.getPassword())) {
            throw new UserLoginException("Invalid password");
        }

        return target;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
