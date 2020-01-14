package com.joezeo.atcrowdfunding.potal.service.impl;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.MD5Util;
import com.joezeo.atcrowdfunding.manager.service.CertService;
import com.joezeo.atcrowdfunding.potal.mapper.MemberMapper;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private CertService certService;

    public Member login(Map<String, Object> loginInfo) {
        if(loginInfo == null){
            throw new ServiceException("传入参数不可为null");
        }

        // 使用MD5加密密码
        String md5Pswd = MD5Util.digest((String) loginInfo.get("userpswd"));
        loginInfo.put("userpswd", md5Pswd);

        Member member = memberMapper.selectByLoginInfo(loginInfo);
        if(member == null){
            throw new ServiceException("登录失败，请验证账号密码后重试");
        }
        return member;
    }

    public void updateMember(Member member) {
        if(member == null){
            throw new ServiceException("传入的member参数不可为null");
        }

        int count = memberMapper.updateByPrimaryKeySelective(member);
        if(count != 1){
            throw new ServiceException("更新会员数据失败");
        }
    }

    public Member queryByMemberid(Integer memberid) {
        if(memberid == null || memberid <= 0){
            throw new ServiceException("id值异常");
        }
        Member member = memberMapper.selectByPrimaryKey(memberid);
        if(member == null){
            throw new ServiceException("查询用户信息失败");
        }
        return member;
    }


}
