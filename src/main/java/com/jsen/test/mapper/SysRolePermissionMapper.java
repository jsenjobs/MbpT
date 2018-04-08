package com.jsen.test.mapper;

import com.jsen.test.entity.SysRolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    int createRolePermission(SysRolePermission sysRolePermission);

    int deleteRolePermissionById(int id);
}
