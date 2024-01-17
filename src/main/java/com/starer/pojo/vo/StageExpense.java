package com.starer.pojo.vo;


public class StageExpense {

    // 项目的所有阶段名称
    private String[] stages;
    // 项目每个阶段所对应的已支出成本
    private String[] expenses;

    public StageExpense(String[] stages, String[] expenses) {
        this.stages = stages;
        this.expenses = expenses;
    }



    public String[] getStages() {
        return stages;
    }

    public void setStages(String[] stages) {
        this.stages = stages;
    }

    public String[] getExpenses() {
        return expenses;
    }

    public void setExpenses(String[] expenses) {
        this.expenses = expenses;
    }
}
