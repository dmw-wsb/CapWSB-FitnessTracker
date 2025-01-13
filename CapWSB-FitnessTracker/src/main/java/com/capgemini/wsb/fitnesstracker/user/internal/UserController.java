package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.dto.NewUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UserSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.internal.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userService.findAllUsers());
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userMapper.toSimpleDtoList(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.findUserById(id));
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userMapper.toDtoList(userService.findUserByEmail(email));
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable String date) {
        LocalDate thresholdDate = LocalDate.parse(date);
        return userMapper.toDtoList(userService.findUsersOlderThan(thresholdDate));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody NewUserDto newUserDto) {
        userService.createUser(newUserDto); // Pass the DTO directly
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(id, updateUserDto); // Pass the DTO directly
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
