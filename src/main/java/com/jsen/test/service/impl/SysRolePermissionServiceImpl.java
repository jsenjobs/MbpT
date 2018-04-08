package com.jsen.test.service.impl;

import com.jsen.test.entity.SysRolePermission;
import com.jsen.test.mapper.SysRolePermissionMapper;
import com.jsen.test.service.SysRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
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

    @Override
    public ResponseBase createRolePermission(int r_id, int p_id) {
        return ResponseBase.create().code(0).add("eff", baseMapper.createRolePermission(new SysRolePermission().setPermissionId(p_id).setRoleId(r_id)));
    }

    @Override
    public ResponseBase deleteRolePermission(int id) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteRolePermissionById(id));
    }
}
