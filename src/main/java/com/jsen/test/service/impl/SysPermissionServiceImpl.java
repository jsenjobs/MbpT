package com.jsen.test.service.impl;

import com.jsen.test.entity.SysPermission;
import com.jsen.test.mapper.SysPermissionMapper;
import com.jsen.test.service.SysPermissionService;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Override
    public ResponseBase createPermission(String permission) {
        return ResponseBase.create().code(0).add("eff", baseMapper.createPermission(new SysPermission().setPermission(permission)));
    }

    @Override
    public ResponseBase deletePermission(String permission) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deletePermission(permission));
    }
}
