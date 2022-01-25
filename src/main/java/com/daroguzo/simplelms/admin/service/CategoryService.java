package com.daroguzo.simplelms.admin.service;

import com.daroguzo.simplelms.admin.dto.CategoryDto;
import com.daroguzo.simplelms.admin.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();

    /**
     *  신규 카데고리 추가
     */
    boolean add(String categoryName);

    /**
     *  카데고리 수정
     */
    boolean update(CategoryDto categoryDto);

    /**
     *  카데고리 삭제
     */
    boolean delete (Long id);
}
