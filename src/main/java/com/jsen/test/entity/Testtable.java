package com.jsen.test.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableLogic;
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
public class Testtable extends Model<Testtable> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("Name")
    private String Name;
    @TableField("Sex")
    private String Sex;
    @TableField("TestInt")
    private Integer TestInt;
    @TableField("TestChar")
    private String TestChar;
    @TableField("TestNumber")
    private Float TestNumber;
    @TableField(value = "Time", update = "now()")
    private Date Time;

    @TableField(value = "Logical")
    @TableLogic
    private Integer Logical;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
