package com.daroguzo.simplelms.admin.service;

import com.daroguzo.simplelms.admin.dto.CategoryDto;
import com.daroguzo.simplelms.admin.entity.Category;
import com.daroguzo.simplelms.admin.model.CategoryInput;
import com.daroguzo.simplelms.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<CategoryDto> list() {
        List<Category> all = categoryRepository.findAll(getSortBySortValueDesc());
        return CategoryDto.of(all);
    }

    @Transactional
    @Override
    public boolean add(String categoryName) {

        Category newCategory = Category.builder()
                .categoryName(categoryName)
                .isUsing(true)
                .sortValue(0)
                .build();

        categoryRepository.save(newCategory);

        return false;
    }

    @Transactional
    @Override
    public boolean update(CategoryInput input) {
        Optional<Category> byId = categoryRepository.findById(input.getId());
        if (byId.isEmpty()) {
            return false;
        }

        System.out.println(input.isUsing());

        Category category = byId.get();
        category.setCategoryName(input.getCategoryName());
        category.setSortValue(input.getSortValue());
        category.setUsing(input.isUsing());
        categoryRepository.save(category);
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
