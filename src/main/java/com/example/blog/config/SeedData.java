package com.example.blog.config;

import com.example.blog.models.Post;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception{
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){
            Post post1 = new Post();
            post1.setTitle("title of post1");
            post1.setBody("this is the body of post1");

            Post post2 = new Post();
            post2.setTitle("title of post2");
            post2.setBody("this is the body of post2");

            postService.save(post1);
            postService.save(post2);

        }
    }


}
