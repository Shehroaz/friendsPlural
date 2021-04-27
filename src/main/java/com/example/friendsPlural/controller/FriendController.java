package com.example.friendsPlural.controller;

import com.example.friendsPlural.model.Friend;
import com.example.friendsPlural.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    public Friend create(@RequestBody Friend friend){
        return friendService.save(friend);
    }

    @GetMapping("/friend")
    public Iterable<Friend> read(){
        return friendService.findAll();
//        Iterable<Friend> iterable = friendService.findAll();
//        iterable.forEach(friend -> System.out.println(friend.getFirstName() + " " + friend.getLastName()));
//        return iterable;
    }

    @PutMapping("/friend")
    public Friend update (@RequestBody Friend friend){
        return friendService.save(friend);
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
