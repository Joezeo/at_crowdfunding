package com.joezeo.atcrowdfunding.potal.mapper;

import com.joezeo.atcrowdfunding.bean.MemberAddress;

public interface MemberAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    MemberAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);
}