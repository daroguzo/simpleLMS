package com.daroguzo.simplelms.admin.mapper;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam memberParam);
    List<MemberDto> selectList(MemberParam memberParam);

}
