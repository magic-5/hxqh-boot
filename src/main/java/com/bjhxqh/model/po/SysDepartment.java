package com.bjhxqh.model.po;

import javax.persistence.*;

@Table(name = "sys_department")
public class SysDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer parentid;

    private String name;

    private String departmenttype;

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
     * @return parentid
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return departmenttype
     */
    public String getDepartmenttype() {
        return departmenttype;
    }

    /**
     * @param departmenttype
     */
    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }
}