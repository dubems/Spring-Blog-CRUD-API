package com.dubems.repository;

import com.dubems.model.Blog;
import org.springframework.data.repository.CrudRepository;



public interface BlogRepository extends CrudRepository<Blog,Long> {

    Blog findBlogById(Long id);
}
