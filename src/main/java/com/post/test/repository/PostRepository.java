package com.post.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.post.test.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
