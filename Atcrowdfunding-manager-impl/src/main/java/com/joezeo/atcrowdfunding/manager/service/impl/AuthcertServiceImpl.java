package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.manager.service.AuthcertService;
import com.joezeo.atcrowdfunding.potal.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthcertServiceImpl implements AuthcertService {

    @Resource
    private MemberMapper memberMapper;

    public Member queryMemberByPiid(String processInstanceId) {
        if(processInstanceId == null || "".equals(processInstanceId)){
            throw new ServiceException("传入的参数processInstanceId不可为空");
        }

        Member member = memberMapper.selectMemberByPiid(processInstanceId);
        if(member == null){
            throw new ServiceException("查询审核流程相应会员信息失败");
        }
        return member;
    }
}
