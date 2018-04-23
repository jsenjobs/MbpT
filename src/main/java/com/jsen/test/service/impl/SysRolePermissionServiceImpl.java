package com.jsen.test.service.impl;

import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysRolePermission;
import com.jsen.test.entity.SysUserRole;
import com.jsen.test.mapper.SysPermissionMapper;
import com.jsen.test.mapper.SysRolePermissionMapper;
import com.jsen.test.service.SysRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public ResponseBase createRolePermission(int r_id, int p_id) {


        if (baseMapper.getPermissionByRoleIdAndPermissionId(r_id, p_id) != null) {
            return ResponseBase.create().code(1).msg("该角色已拥有该权限");
        }

        int eff = baseMapper.insertRolePermission(new SysRolePermission().setRoleId(r_id).setPermissionId(p_id));
        SysPermission sysPermission = sysPermissionMapper.getPermissionById(p_id);
        return ResponseBase.create().code(0).add("eff", eff).add("data", sysPermission);
    }

    @Override
    public ResponseBase deleteRolePermission(int roleId, int permissionId) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteRolePermissionByRoleIdAndPermissionId(roleId, permissionId));
    }
}
