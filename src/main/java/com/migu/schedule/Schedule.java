package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*类名和方法不能修改
 */
public class Schedule
{
    // 虚拟注册中心
    public static Map<String, TaskInfo> taskInfoMap = new HashMap<String, TaskInfo>();
    
    // 挂起队列
    public static Map<Integer, Integer> waitMap = new HashMap<Integer, Integer>();
    
    public int init()
    {
        
        if (null != taskInfoMap || taskInfoMap.size() > 0)
        {
            taskInfoMap.clear();
            // 初始化成功
            return ReturnCodeKeys.E001;
        }
        // 方法未实现
        return ReturnCodeKeys.E000;
    }
    
    public int registerNode(int nodeId)
    {
        // 服务节点编号非法
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        // 服务节点编号已注册
        if (!taskInfoMap.isEmpty() && taskInfoMap.containsKey(String.valueOf(nodeId)))
        {
            return ReturnCodeKeys.E005;
        }
        for (int i = 1; i < 3; i++)
        {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setTaskId(i);// 任务编号
            taskInfo.setNodeId(nodeId);// 服务节点编号
            taskInfoMap.put(String.valueOf(nodeId), taskInfo);
        }
        return ReturnCodeKeys.E003;
    }
    
    public int unregisterNode(int nodeId)
    {
        // 服务节点编号非法
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        if (!taskInfoMap.isEmpty() && !taskInfoMap.containsKey(String.valueOf(nodeId)))
        {
            return ReturnCodeKeys.E007;
        }
        return ReturnCodeKeys.E006;
    }
    
    public int addTask(int taskId, int consumption)
    {
        // 任务编号非法
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        // 任务已经被添加
        if (null != waitMap && waitMap.containsKey(taskId))
        {
            return ReturnCodeKeys.E010;
        }
        waitMap.put(taskId, consumption);
        return ReturnCodeKeys.E008;
    }
    
    public int deleteTask(int taskId)
    {
        // 任务编号非法
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        // 任务不存在
        if (null != waitMap && !waitMap.containsKey(taskId))
        {
            return ReturnCodeKeys.E012;
        }
        waitMap.remove(taskId);
        return ReturnCodeKeys.E011;
    }
    
    public int scheduleTask(int threshold)
    {
        if (threshold <= 0)
        {
            return ReturnCodeKeys.E002;
        }
        return ReturnCodeKeys.E013;
    }
    
    public int queryTaskStatus(List<TaskInfo> tasks)
    {
        // 参数列表非法
        if (null != tasks && tasks.isEmpty())
        {
            return ReturnCodeKeys.E016;
        }
        return ReturnCodeKeys.E015;
    }
    
}
