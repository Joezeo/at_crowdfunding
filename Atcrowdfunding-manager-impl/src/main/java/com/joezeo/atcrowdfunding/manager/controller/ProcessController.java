package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/process/")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("doQueryPage")
    @ResponseBody
    public JsonResult doQueryPage(Integer pageNum, Integer pageSize, String name) {
        JsonResult result = null;
        PageInfo pageInfo = new PageInfo(pageSize, pageNum);

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        try {
            List<ProcessDefinition> processDefinitions = null;
            int count = 0;

            if(name == null){
                processDefinitions = query.listPage(pageInfo.getStartIndex(), pageSize);
                count = ((Long) query.count()).intValue();

            } else {
                processDefinitions = query.processDefinitionName(name).listPage(pageInfo.getStartIndex(), pageSize);
                count = processDefinitions.size();
            }

            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            for(ProcessDefinition pd : processDefinitions){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", pd.getId());
                map.put("name", pd.getName());
                map.put("version", pd.getVersion());
                map.put("key", pd.getKey());
                datas.add(map);
            }

            pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
            pageInfo.setDatas(datas);

            result = new JsonResult(pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doAddProcess")
    @ResponseBody
    public JsonResult doAddProcess(HttpServletRequest request){
        JsonResult result = null;

        try{
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            MultipartFile processpic = mreq.getFile("processpic");

            repositoryService.createDeployment().addInputStream(processpic.getOriginalFilename(), processpic.getInputStream()).deploy();

            result = new JsonResult(true);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doLoadProcessPic")
    @ResponseBody
    public void doLoadProcessPic(HttpServletResponse response, String id){
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
            InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());

            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(resourceAsStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("doDeleteProcess")
    @ResponseBody
    public JsonResult doDeleteProcess(String id){
        JsonResult result = null;

        try{
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);

            result = new JsonResult(true);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
