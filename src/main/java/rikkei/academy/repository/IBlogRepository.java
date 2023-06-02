package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Blog;

import java.util.List;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    @Query("SELECT b FROM Blog AS b WHERE b.title LIKE CONCAT('%',:title,'%')")
    Page<Blog> findByTitleBlog(@Param("title") String title, Pageable pageable);
}
