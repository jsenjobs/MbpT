package com.jsen.test.service.impl;

import com.jsen.test.entity.SysUserRole;
import com.jsen.test.mapper.SysUserRoleMapper;
import com.jsen.test.service.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public ResponseBase createUserRole(int u_id, int r_id) {
        return ResponseBase.create().code(0).add("eff", baseMapper.createUserRole(new SysUserRole().setRoleId(r_id).setUserId(u_id)));
    }

    @Override
    public ResponseBase deleteUserRole(int id) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteUserRoleById(id));
    }
}
