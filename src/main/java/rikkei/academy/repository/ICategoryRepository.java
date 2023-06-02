package rikkei.academy.repository;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.springframework.data.repository.PagingAndSortingRepository;
import rikkei.academy.model.Category;



public interface ICategoryRepository extends PagingAndSortingRepository<Category,Long> {

}

