package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User createUser(final User user) {
        log.info("Attempting to create user: {}", user);
        if (user.getId() != null) {
            log.error("Failed to create a user with pre-set ID: {}", user.getId());
            throw new IllegalArgumentException("Cannot create user with pre-set ID. Use update for existing users.");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        log.info("Retrieving user with ID: {}", userId);
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        log.info("Retrieving user by email: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Attempting to delete user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("Attempted to delete non-existent user with ID: {}", id);
            throw new UserNotFoundException("No user found with ID: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        log.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setBirthdate(updatedUser.getBirthdate());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving or updating user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public List<User> findAllUsersOlderThan(LocalDate time) {
        return userRepository.findAllByBirthdateBefore(time);
    }


}

