package com.post.test.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.post.test.service.PostService;

@Component
public class PostRunner implements CommandLineRunner{

	@Autowired PostService postSvc;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("run...");
		postSvc.retrieveAndSavePosts();
	}
}
