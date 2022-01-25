package com.daroguzo.simplelms.admin.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
@Entity
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String categoryName;
    private int sortValue;

    private boolean isUsing;

}
