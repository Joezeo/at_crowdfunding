package com.joezeo.activiti;

import com.joezeo.atcrowdfunding.web.activiti.listener.NoListener;
import com.joezeo.atcrowdfunding.web.activiti.listener.YesListener;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activiti_Test_2 {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");
    ProcessEngine processEngine = ioc.getBean("processEngine", ProcessEngine.class);

    @Test
    public void deployProcess(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("testProcess_2.bpmn").deploy();

        System.out.println(deploy);
    }

    @Test
    public void queryProcessDefination(){
        ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();

        ProcessDefinition processDefinition = query.latestVersion().singleResult();

        System.out.println(processDefinition.getId());
        System.out.println(processDefinition.getKey());
        System.out.println(processDefinition.getName());
    }

    @Test
    public void startProcessInstance(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        ProcessDefinition processDefinition = query.processDefinitionKey("testProcess_2").singleResult();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> map = new HashMap<>();
        map.put("yesListener", new YesListener());
        map.put("noListener", new NoListener());

        runtimeService.startProcessInstanceById(processDefinition.getId(), map);

        System.out.println("启动流程："+processDefinition.getId()+" 成功");
    }

    //分配角色完成任务
    @Test
    public void completeTask(){
        TaskService taskService = processEngine.getTaskService();

//        taskService.setAssignee("104", "joezeo");
        List<Task> list = taskService.createTaskQuery().taskAssignee("joezeo").list();

        for(Task task : list){
            taskService.setVariable(task.getId(), "flag", "false");
            System.out.println(task.getName());
            taskService.complete(task.getId());
        }
    }
}
