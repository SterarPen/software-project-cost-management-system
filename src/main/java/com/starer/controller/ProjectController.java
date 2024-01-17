package com.starer.controller;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.entity.Demand;
import com.starer.pojo.entity.Stage;
import com.starer.pojo.entity.Task;
import com.starer.pojo.vo.StageExpense;
import com.starer.pojo.vo.StageTaskNum;
import com.starer.service.IDemandService;
import com.starer.service.IStageService;
import com.starer.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/json")
public class ProjectController {

    private final IStageService stageService;
    private final ITaskService taskService;
    private IDemandService demandService;

    @Autowired
    public ProjectController(IStageService stageService, ITaskService taskService, IDemandService demandService) {
        this.stageService = stageService;
        this.taskService = taskService;
        this.demandService = demandService;
    }

    /**
     * 返回一个项目所有阶段名称和每个阶段的已支出成本
     * StageException包含所有阶段名称的数组和每个阶段已支出成本的数组，阶段和已支出成本是根据数组下标一一对应
     * @param project_id 发起请求的用户查询的项目id
     * @param req 请求对象
     * @return 一个由StageExpense转换的JSON字符串(一个数据域为StageExpense的Result的对象)
     */
    @RequestMapping(value = "/project_cost/{project_id}", produces = {
            "application/json; charset=utf-8"})
    public String getProjectCost(@PathVariable String project_id, HttpServletRequest req) {
        // 通过项目ID获取当前项目包含的所有阶段
        Stage[] stages = stageService.queryAllStagesForAProject(project_id);
        // 存放所有阶段名的字符串数组
        String[] stagesName = new String[stages == null ? 0 : stages.length];
        // 存放每个阶段的已支出成本的数组
        String[] expenses = new String[stages == null ? 0 : stages.length];
        // 遍历所有阶段对象
        for (int i = 0; i < stagesName.length; i++) {
            stagesName[i] = stages[i].getStageName();
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            BigDecimal stageExpense = new BigDecimal("0");
            for (Task task : tasks) {
                // 如果存在已支出成本，则将该成本加入到当前阶段的已支出总成本中
                if(task.getCost() != null)  stageExpense = stageExpense.add(task.getCost());
            }
            expenses[i] = stageExpense.toString();
        }

        return JSON.toJSONString(new StageExpense(stagesName, expenses));
    }

    /**
     * 返回一个项目所有阶段名称和每个阶段的已完成任务的数量和未完成任务的数量
     * StageTaskNum包括所有阶段名称的数组、每个阶段的已完成任务的数量和的数组和未完成任务的数量数组，
     *  阶段和已支出成本是根据数组下标一一对应
     * @param project_id 发起请求的用户查询的项目的id
     * @param req 请求对象
     * @return 一个由StageTaskNum转换的JSON字符串
     */
    @RequestMapping(value = "/project_task_num/{project_id}", produces = {
            "application/json; charset=utf-8"})
    public String getProjectTakNum(@PathVariable String project_id, HttpServletRequest req) {
        Stage[] stages = stageService.queryAllStagesForAProject(project_id);
        String[] stagesName = new String[stages != null ? stages.length : 0];
        int[] finishTaskNum = new int[stages != null ? stages.length : 0];
        int[] noFinishTaskNum = new int[stages != null ? stages.length : 0];

        for (int i = 0; i < stagesName.length; i++) {
            stagesName[i] = stages[i].getStageName();
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            for (Task task : tasks) {
                if(task.getStatus() == 1) {
                    finishTaskNum[i]++;
                } else {
                    noFinishTaskNum[i]++;
                }
            }
        }

        return JSON.toJSONString(new StageTaskNum(stagesName, finishTaskNum, noFinishTaskNum));
    }

    @RequestMapping(value = "/project_requirement/{projectId}", produces = {
            "application/json; charset=utf-8"})
    public String getProjectRequirement(@PathVariable String projectId) {
        Demand[] demands = demandService.queryDemandByProjectId(projectId);
        return JSON.toJSONString(demands);
    }

    @RequestMapping(value = "/project_stages/{projectId}", produces = {
            "application/json; charset=utf-8"})
    public String getProjectStages(@PathVariable String projectId) {
        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        return JSON.toJSONString(stages);
    }

    @RequestMapping(value = "/project_tasks/{projectId}", produces = {
            "application/json; charset=utf-8"})
    public String getProjectTasks(@PathVariable String projectId) {
        Task[] tasks = taskService.selectTaskByProjectId(projectId);
        return JSON.toJSONString(tasks);
    }
}
