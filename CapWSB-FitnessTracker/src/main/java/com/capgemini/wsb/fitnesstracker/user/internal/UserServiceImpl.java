package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.api.dto.NewUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findUsersOlderThan(LocalDate thresholdDate) {
        return userRepository.findByBirthdateBefore(thresholdDate);
    }

    @Override
    public User createUser(NewUserDto newUserDto) {
        User user = new User(
                newUserDto.firstName(),
                newUserDto.lastName(),
                newUserDto.birthdate(),
                newUserDto.email()
        );
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UpdateUserDto updatedUserDto) {
        User user = findUserById(id);
        user.updateUser(
                updatedUserDto.getFirstName(),
                updatedUserDto.getLastName(),
                updatedUserDto.getBirthdate(),
                updatedUserDto.getEmail()
        );
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
