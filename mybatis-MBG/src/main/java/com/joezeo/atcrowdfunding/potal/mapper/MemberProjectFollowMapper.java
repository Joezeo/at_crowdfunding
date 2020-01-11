package com.joezeo.atcrowdfunding.potal.mapper;

import com.joezeo.atcrowdfunding.bean.MemberProjectFollow;

public interface MemberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberProjectFollow record);

    int insertSelective(MemberProjectFollow record);

    MemberProjectFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberProjectFollow record);

    int updateByPrimaryKey(MemberProjectFollow record);
}