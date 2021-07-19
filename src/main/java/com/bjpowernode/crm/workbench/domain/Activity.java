package com.bjpowernode.crm.workbench.domain;

public class Activity {


    private String id;
    private String OWNER;
    private String NAME;
    private String startDate;
    private String endDate;
    private String cost;
    private String description;
    private String createTime;
    private String createBy;
    private String editTime;
    private String editBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", OWNER='" + OWNER + '\'' +
                ", NAME='" + NAME + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", cost='" + cost + '\'' +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}
