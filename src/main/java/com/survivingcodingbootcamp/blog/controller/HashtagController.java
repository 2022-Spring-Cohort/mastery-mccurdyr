package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {
    private HashtagRepository hashRepo;

    public HashtagController(HashtagRepository hashRepo) {
        this.hashRepo = hashRepo;
    }


    @GetMapping("")
    public String displayHashtagPage(Model model) {
        model.addAttribute("hashtags", hashRepo.findAll());
        return "all-hashtags-template";
    }

    @GetMapping("/{id}")
    public String displaySingleHashtag(@PathVariable long id, Model model) {
        model.addAttribute("hashtag", hashRepo.findById(id).get());
        return "single-hashtag-template";
    }


    //Reference reviews.AnimeController, line 51 and Optional in HashRepo

//    @PostMapping("/post/{id}/addhashtag")
//    public String addHashtag(@PathVariable long id, @RequestParam String hashtag){
//        Post myPost = postRepo
//    }


}