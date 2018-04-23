package com.jsen.test.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Data
@Accessors(chain = true)
public class HcModelShare implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private byte[] modelData;
    private Integer creator;
    private Integer collect;
    private Integer look;
    private String name;
    private Date createTime;
    private String intro;

}
