package com.bjhxqh.model.po;

import javax.persistence.*;

@Table(name = "sys_authgroup_resource")
public class SysAuthgroupResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer groupid;

    private Integer resourceid;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return groupid
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * @param groupid
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * @return resourceid
     */
    public Integer getResourceid() {
        return resourceid;
    }

    /**
     * @param resourceid
     */
    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }
}