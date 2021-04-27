package com.example.friendsPlural;

import com.example.friendsPlural.controller.FriendController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class FriendsPluralApplicationTests {
	@Autowired
	FriendController friendController;

	@Test
	void contextLoads() {
		Assert.notNull(friendController);
	}

}
