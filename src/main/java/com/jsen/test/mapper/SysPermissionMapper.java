package com.jsen.test.mapper;

import com.jsen.test.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    int createPermission(SysPermission sysPermission);

    int deletePermission(String permission);
}
