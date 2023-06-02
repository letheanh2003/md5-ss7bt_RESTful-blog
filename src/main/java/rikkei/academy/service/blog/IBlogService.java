package rikkei.academy.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Blog;
import rikkei.academy.service.IGenericService;

public interface IBlogService extends IGenericService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    Page<Blog> findByTitleBlog(@Param("title") String title, Pageable pageable);
}
