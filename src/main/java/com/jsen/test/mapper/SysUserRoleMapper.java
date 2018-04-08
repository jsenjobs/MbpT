package com.jsen.test.mapper;

import com.jsen.test.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    int createUserRole(SysUserRole sysUserRole);

    int deleteUserRoleById(int id);
}
