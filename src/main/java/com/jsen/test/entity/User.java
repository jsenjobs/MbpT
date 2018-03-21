package com.jsen.test.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@Data
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("Name")
    private String Name;
    @TableField("Sex")
    private String Sex;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}