package com.daroguzo.simplelms.admin.dto;

import com.daroguzo.simplelms.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private Long id;

    private String categoryName;
    private int sortValue;

    private boolean isUsing;

    public static List<CategoryDto> of (List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryDtos = new ArrayList<>();
            for (Category c : categories) {
                categoryDtos.add(of(c));
            }
            return categoryDtos;
        }
        return null;
    }

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .isUsing(category.isUsing())
                .build();
    }
}
