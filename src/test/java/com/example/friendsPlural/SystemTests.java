package com.example.friendsPlural;

import com.example.friendsPlural.model.Address;
import com.example.friendsPlural.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


public class SystemTests {

    @Test
    public void checkCreateReadDelete(){
        RestTemplate restTemplate = new RestTemplate();
        String url1 = "http://localhost:9901/friend";
        List<Address> addressList = Arrays.asList(new Address(200 , "lane no 121", "GujjarKhan"),
                new Address(201 , "lane no 1111" , "Pindi"));
        Friend friend = new Friend(1000 , "King" , "khan" , false , 23, addressList);
        ResponseEntity<Friend> responseEntity = restTemplate.postForEntity(url1 , friend , Friend.class);
        Friend [] friends = restTemplate.getForObject(url1 , Friend[].class);
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("King");

        restTemplate.delete(url1 + "/" + responseEntity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url1 , Friend [].class)).isEmpty();
    }
}
