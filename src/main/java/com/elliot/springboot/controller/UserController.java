package com.elliot.springboot.controller;

import com.elliot.springboot.dto.UserDto;
import com.elliot.springboot.entity.User;
import com.elliot.springboot.exception.ErrorDetails;
import com.elliot.springboot.exception.ResourceNotFoundException;
import com.elliot.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    // build create User REST API
    //http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //build get all users REST API
    //http://localhost:8080/api/users
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users =  userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //build update user REST API
    //http://localhost:8080/api/users/1
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //build delete user REST API
    //http://localhost:8080/api/users/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }

}
