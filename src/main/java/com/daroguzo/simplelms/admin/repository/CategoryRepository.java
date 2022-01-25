package com.daroguzo.simplelms.admin.repository;

import com.daroguzo.simplelms.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
