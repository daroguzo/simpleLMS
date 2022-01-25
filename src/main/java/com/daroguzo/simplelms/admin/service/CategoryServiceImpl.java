package com.daroguzo.simplelms.admin.service;

import com.daroguzo.simplelms.admin.dto.CategoryDto;
import com.daroguzo.simplelms.admin.entity.Category;
import com.daroguzo.simplelms.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> list() {
        List<Category> all = categoryRepository.findAll();
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
    public boolean update(CategoryDto categoryDto) {
        return false;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        return false;
    }
}
