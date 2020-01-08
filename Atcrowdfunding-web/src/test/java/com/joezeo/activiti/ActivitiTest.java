package com.joezeo.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ActivitiTest {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");
    ProcessEngine processEngine = ioc.getBean("processEngine", ProcessEngine.class);

    // 创建流程引擎
    @Test
    public void createEngineTest(){
        System.out.println(processEngine);
    }

    // 通过bmpn流程图，创建流程定义,即部署流程
    @Test
    public void createDeploymentTest(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("leaveProcess.bpmn").deploy();
        System.out.println(deploy);
    }

    // 查询流程定义
    @Test
    public void queryProcessDefinationTest(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = query.latestVersion().singleResult();
        System.out.println(processDefinition.getId());
        System.out.println(processDefinition.getKey());
    }

    // 创建流程实例
    @Test
    public void startProcessInstanceTest(){
        ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        System.out.println(processInstance);
    }

    // 查询流程实例，完成任务
    @Test
    public void compelteTask(){
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();

        List<Task> list = taskQuery.taskAssignee("joezeo").list();

        for(Task task : list){
            System.out.println(task.getName());
            System.out.println(task.getAssignee());

            // 完成任务
            taskService.complete(task.getId());
            System.out.println("joezeo已完成审批");
        }


    }

}
