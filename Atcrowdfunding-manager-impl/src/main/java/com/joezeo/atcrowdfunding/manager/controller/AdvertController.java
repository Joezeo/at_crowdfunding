package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.Advertisement;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/advert/")
public class AdvertController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("doPageQuery")
    @ResponseBody
    public JsonResult doQueryPage(String name, Integer pageSize, Integer pageNum){
        JsonResult result = null;

        try{
            PageInfo pageInfo = advertisementService.queryAdvertByPage(name, pageSize, pageNum);
            result = new JsonResult(pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doAddAdvert")
    @ResponseBody
    public JsonResult doAddAdvert(HttpServletRequest request, Advertisement advert , HttpSession session){
        JsonResult result = null;

        try{
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;

            MultipartFile mfile = mreq.getFile("advpic");

            String name = mfile.getOriginalFilename();//java.jpg
            String extname = name.substring(name.lastIndexOf(".")); // .jpg

            String iconpath = UUID.randomUUID().toString()+extname;

            ServletContext servletContext = session.getServletContext();
            String realpath = servletContext.getRealPath("/pics");

            String path =realpath+ "\\adv\\"+iconpath;
            mfile.transferTo(new File(path));

            User user = (User)session.getAttribute(Const.LOGIN_USER);
            advert.setUserid(user.getId());
            advert.setStatus("1");
            advert.setIconpath(iconpath);

            advertisementService.addAdvert(advert);
            result = new JsonResult(true);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
