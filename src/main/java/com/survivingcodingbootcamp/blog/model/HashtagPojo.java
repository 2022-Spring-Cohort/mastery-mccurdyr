package com.survivingcodingbootcamp.blog.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
public class HashtagPojo {
    @Id
    @GeneratedValue
    private Long id;
    private String hashName;

    @ManyToMany(mappedBy = "hashtags")
    private Collection<Post> posts;

    protected HashtagPojo() {}

    public HashtagPojo(String hashName) {
        this.hashName = hashName;
        this.posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getHashName() {
        return hashName;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashtagPojo that = (HashtagPojo) o;
        return Objects.equals(id, that.id) && Objects.equals(hashName, that.hashName) && Objects.equals(posts, that.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hashName, posts);
    }
}
