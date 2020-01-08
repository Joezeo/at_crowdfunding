package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.Cert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cert record);

    int insertSelective(Cert record);

    Cert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cert record);

    int updateByPrimaryKey(Cert record);

    List<Cert> selectByPage(Map<String, Object> map);

    int selectCount(@Param("name") String name);
}