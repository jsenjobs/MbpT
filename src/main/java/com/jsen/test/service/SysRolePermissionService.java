package com.jsen.test.service;

import com.jsen.test.entity.SysRolePermission;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    ResponseBase createRolePermission(int r_id, int p_id);
    ResponseBase deleteRolePermission(int roleId, int permissionId);

}
