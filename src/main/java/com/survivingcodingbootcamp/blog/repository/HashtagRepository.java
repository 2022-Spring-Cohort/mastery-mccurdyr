package com.survivingcodingbootcamp.blog.repository;

import com.survivingcodingbootcamp.blog.model.HashtagPojo;
import com.survivingcodingbootcamp.blog.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashtagRepository extends CrudRepository<HashtagPojo, Long> {

    Optional<HashtagPojo> findByHashName(String hashName);
}
