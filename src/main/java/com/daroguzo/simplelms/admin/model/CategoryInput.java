package com.daroguzo.simplelms.admin.model;

import lombok.Data;

@Data
public class CategoryInput {

    Long id;
    String categoryName;
    int sortValue;
    boolean isUsing;
}
