package com.example.friendsPlural;

import com.example.friendsPlural.controller.FriendController;
import com.example.friendsPlural.model.Address;
import com.example.friendsPlural.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class IntegrationsTest {

    @Autowired
    FriendController friendController;

    @Test
    public void createReadDelete(){
        List<Address> addressList = Arrays.asList(new Address(200 , "lane no 121", "GujjarKhan"),
                new Address(201 , "lane no 1111" , "Pindi"));
        Friend friend = new Friend(1000 , "Kings" , "khan" , false , 23, addressList);

        Friend resultFriend = friendController.create(friend);
        System.out.println(resultFriend.getId());

        Iterable<Friend> friends = friendController.read();
        Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("firstName" , "Faizan");

        friendController.delete(resultFriend.getId());
        Assertions.assertThat(friendController.read()).isEmpty();

    }

}
