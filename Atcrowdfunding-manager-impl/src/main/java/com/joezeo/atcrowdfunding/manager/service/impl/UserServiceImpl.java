package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.exception.LoginServiceException;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.MD5Util;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.UserMapper;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryLogin(Map<String, Object> loginInfo) {
        if (loginInfo == null) {
            throw new LoginServiceException("ERROR：登录信息不可为空");
        }

        // 使用MD5加密密码
        String md5Pswd = MD5Util.digest((String) loginInfo.get("userpswd"));
        loginInfo.put("userpswd", md5Pswd);

        User user = userMapper.selectByLoginInfo(loginInfo);

        if (user == null) {
            throw new LoginServiceException("用户名或密码错误~");
        }

        return user;
    }

    public PageInfo queryUserByPage(Integer pageNum, Integer pageSize, String loginAcct) {
        if (pageNum == null || pageSize == null || pageNum <= 0 || pageSize <= 0) {
            throw new ServiceException("传入分页参数异常，pageNum=" + pageNum + ",pageSize=" + pageSize);
        }

        //因为如果loginAcct传来的是%
        //如果不对其进行转译那么他就会把所有数据都查询出来 不安全
        if("%".equals(loginAcct)){
            // java代码中/本身也需要转译 两个/代表一个/
            // 又由于mysql数据库中/也需要转译
            // 所以理论上需要传入//%
            // 加上转译就是 ////%
            loginAcct = "////%";
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageInfo", pageInfo);
        paramMap.put("loginAcct", loginAcct);

        List<User> list = userMapper.selectByPage(paramMap);
        if (list == null) {
            throw new ServiceException("获取分页数据异常，list=null");
        }
        pageInfo.setDatas(list);

        int count = userMapper.selectUserCount(loginAcct);
        if (count < 0) {
            throw new ServiceException("获取用户数量异常");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void insUser(User user) {
        // 前台传来的用户数据有 loginAcct、username、email
        if (user == null) {
            throw new ServiceException("插入的用户信息为空");
        }

        String md5Pswd = MD5Util.digest(Const.DEFAULT_INIT_PASSWORD);
        user.setUserpswd(md5Pswd);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
        user.setCreatetime(date);

        int index = userMapper.insert(user);

        if (index != 1) {
            throw new ServiceException("添加用户异常");
        }
    }
}
