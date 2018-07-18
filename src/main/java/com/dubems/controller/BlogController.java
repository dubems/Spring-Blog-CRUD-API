package com.dubems.controller;

import com.dubems.model.Blog;
import com.dubems.repository.BlogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class BlogController {

    private BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/")
    public Iterable<Blog> index() {
        return blogRepository.findAll();
    }


    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id) {
        long blogId = Long.parseLong(id);
        return blogRepository.findBlogById(blogId);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        String content = body.get("content");

        return blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@RequestBody Map<String, String> body, @PathVariable String id) {
        String title = body.get("title");
        String content = body.get("content");
        Blog blog = blogRepository.findBlogById(Long.parseLong(id));
        content = (content != null) ? content : blog.getContent();
        title = (title != null) ? title : blog.getTitle();
        blog.setContent(content);
        blog.setTitle(title);
        return blogRepository.save(blog);
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id) {
        Blog blog = blogRepository.findBlogById(Long.parseLong(id));
        blogRepository.delete(blog);
        return true;
    }
}
