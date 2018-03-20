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
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("Name")
    private String Name;
    @TableField("Sex")
    private String Sex;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", Name=" + Name +
        ", Sex=" + Sex +
        "}";
    }
}
