package com.bjhxqh.model.po;

import javax.persistence.*;

@Table(name = "sys_role_authgroup")
public class SysRoleAuthgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleid;

    private Integer groupid;

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
     * @return roleid
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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
}