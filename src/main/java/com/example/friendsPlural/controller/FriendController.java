package com.example.friendsPlural.controller;

import com.example.friendsPlural.model.Friend;
import com.example.friendsPlural.service.FriendService;
import com.example.friendsPlural.utils.ErrorMessage;
import com.example.friendsPlural.utils.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    public Friend create(@Valid @RequestBody Friend friend) {
        return friendService.save(friend);
    }



//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<String> exceptionHandler (ValidationException e){
//        return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
//    }




    @GetMapping("/friend")
    public Iterable<Friend> read(){
        return friendService.findAll();
    }

    @PutMapping("/friend")
    public ResponseEntity<Friend> update (@RequestBody Friend friend){
        if (friendService.findById(friend.getId()).isPresent())
           return  new ResponseEntity(friendService.save(friend) , HttpStatus.OK);
        else
            return new ResponseEntity(friend , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend/{id}")
    public void delete (@PathVariable Integer id){
        friendService.deleteById(id);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> query ( @RequestParam(value = "first" , required = false) String firstName ,
                             @RequestParam(value = "last" , required = false) String lastName){
        if (firstName != null && lastName != null)
        return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if (firstName != null)
            return friendService.findByFirstName(firstName);
        else if (lastName != null)
            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();
    }



    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id){
        return friendService.findById(id);
    }
}
