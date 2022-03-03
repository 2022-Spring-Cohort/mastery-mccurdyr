package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.HashtagPojo;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashRepo;
    private TopicRepository topicRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashRepo, TopicRepository topicRepo) {
        this.postRepo = postRepo;
        this.hashRepo = hashRepo;
        this.topicRepo = topicRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }

    @PostMapping("/{id}/addhashtag")
    public String addHashtag(@PathVariable long id, @RequestParam String hashName){
        Post myPost = postRepo.findById(id).get();
        Optional<HashtagPojo> optHashtag = hashRepo.findByHashName(hashName);

        if (optHashtag.isPresent() && myPost.getHashtags().contains(optHashtag.get())) {
            return "redirect:/posts/" + id;
        }
        else if (optHashtag.isPresent()) {
            myPost.addHashtag(optHashtag.get());
            postRepo.save(myPost);
        }
        else {
            HashtagPojo newHashtag = new HashtagPojo(hashName);
            hashRepo.save(newHashtag);
            myPost.addHashtag(newHashtag);
            postRepo.save(myPost);
        }
        return "redirect:/posts/" + id;
    }

    //TODO add a method to allow for the creation of new posts
    @PostMapping("/{id}/addnewpost")
    public String addNewPost(@PathVariable long id, @RequestParam String title, @RequestParam String author, @RequestParam String content) {
        Optional<Topic> tempTopic = topicRepo.findById(id);
        Optional<Post> newPost = postRepo.findByTitleIgnoreCase(title);

        if (tempTopic.isPresent()) {
            Post tempPost;
            if(newPost.isPresent()){
                tempPost = newPost.get();
            }
            else {
                tempPost = new Post(title, tempTopic.get(), content, author);
            }
            postRepo.save(tempPost);
        }
        return "redirect:/topics/" + id;


    }


}
