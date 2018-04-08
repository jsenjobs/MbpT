package com.jsen.test.mapper;

import com.jsen.test.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    int createRole(SysRole sysRole);

    int deleteRole(String value);
}
