package rikkei.academy.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import rikkei.academy.model.Category;
import rikkei.academy.service.category.ICategoryService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<Category> {
    private ICategoryService categoryService;
    @Autowired
    public CategoryFormatter(ICategoryService catalogService) {
        this.categoryService = catalogService;
    };
    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Optional<Category> optionalCategory = categoryService.findById(Long.parseLong(text));
        return optionalCategory.orElse(null);
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}