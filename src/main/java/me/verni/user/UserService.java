package me.verni.user;

import me.verni.exception.UserLoginException;
import me.verni.exception.UserRegistrationException;
import me.verni.util.EmailValidator;
import me.verni.util.PasswordHasher;
import me.verni.util.PasswordValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUserByName(String name){
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
        if (!EmailValidator.isValidEmail(email)) throw new UserRegistrationException("The email is uncorrect0");

        if (!PasswordValidator.validatePassword(password).isValid())
            throw new UserRegistrationException(PasswordValidator.validatePassword(password).getMessage());

        if(!getUserByName(name).isEmpty()) throw new UserRegistrationException("User with this name is already exists");

        if(getUserByEmail(email).isPresent()) throw new UserRegistrationException("User with this email is already exists");

        if(name.length() <= 3) throw new UserRegistrationException("Name should be at least 4 letters");

        User user = new User(name, email, password);
        saveUser(user);

    }

    public void login(String email, String password) {
        var userOptional = getUserByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UserLoginException("User with this email does not exist");
        }

        User target = userOptional.get();
        if (!target.getPassword().equals(PasswordHasher.hash(password))) {
            throw new UserLoginException("Invalid password");
        }
    }

}
