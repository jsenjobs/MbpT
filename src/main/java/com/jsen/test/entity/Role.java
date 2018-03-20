package com.jsen.test.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jsen
 * @since 2018-03-20
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("Role")
    private Integer Role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer Role) {
        this.Role = Role;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", Role=" + Role +
        "}";
    }
}
