package rikkei.academy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogController")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> provinces() {
        return categoryService.findAll();
    }

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getList() {
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> delete(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateCustomer(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogOptional.get().getId());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }

}