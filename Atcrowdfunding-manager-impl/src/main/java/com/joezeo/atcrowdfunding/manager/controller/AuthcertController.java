package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.AuthcertService;
import com.joezeo.atcrowdfunding.potal.service.MemberCertService;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authcert/")
public class AuthcertController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AuthcertService authcertService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCertService memberCertService;

    @RequestMapping("verify")
    public String htmVerify(Integer memberid, HttpSession session){
        Member member = memberService.queryByMemberid(memberid);
        List<Map<String,Object>> certs = memberCertService.queryCertsByMemberid(memberid);

        session.setAttribute("memberInfo", member);
        session.setAttribute("certsInfo", certs);
        return "authcert/verify";
    }

    @RequestMapping("doQueryPage")
    @ResponseBody
    public JsonResult doQueryPage(Integer pageNum, Integer pageSize) {
        JsonResult result = null;

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);

        try {
            List<Task> tasks = taskService.createTaskQuery()
                    .taskCandidateGroup("manager").processDefinitionKey("verifyProcess")
                    .listPage(pageInfo.getStartIndex(), pageSize);

            // 使用map来存储返回前端的数据
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            for (Task task : tasks) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("taskId", task.getId());
                map.put("taskName", task.getName());

                // 根据Task对象获取流程定义id查询流程定义
                ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                        .processDefinitionId(task.getProcessDefinitionId())
                        .singleResult();
                map.put("prcDefName", processDefinition.getName());
                map.put("prcDefVersion", processDefinition.getVersion());

                // 根据task对象获取流程实例id查询member对象
                Member member = authcertService.queryMemberByPiid(task.getProcessInstanceId());

                map.put("member", member);
                list.add(map);
            }

            pageInfo.setDatas(list);

            int count = ((Long) taskService.createTaskQuery()
                    .taskCandidateGroup("manager").processDefinitionKey("verifyProcess").count()).intValue();
            pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

            result = new JsonResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            result.setMessage("");
            result.setSuccess(false);
        } finally {
            return result;
        }
    }

    @RequestMapping("doVerify")
    @ResponseBody
    public JsonResult doVerify(Boolean flag, String memberid, String taskid){
        JsonResult result = null;

        try{
            // 通过flag完成task
            taskService.setVariable(taskid, "flag", flag);
            taskService.setVariable(taskid, "memberid", memberid);
            taskService.complete(taskid);

            result = new JsonResult("审核完成");
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult();
            result.setSuccess(false);
            result.setMessage("审核失败，请重试");
        } finally {
            return result;
        }
    }
}
