package com.joezeo.atcrowdfunding.potal.mapper;

import com.joezeo.atcrowdfunding.bean.Member;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member selectByLoginInfo(Map<String, Object> loginInfo);

    Member selectMemberByPiid(@Param("piid") String processInstanceId);
}