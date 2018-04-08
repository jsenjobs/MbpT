package com.jsen.test.service.impl;

import com.jsen.test.entity.SysRole;
import com.jsen.test.mapper.SysRoleMapper;
import com.jsen.test.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public ResponseBase createRole(String value) {
        return ResponseBase.create().code(0).add("eff", baseMapper.createRole(new SysRole().setValue(value).setState(1)));
    }

    @Override
    public ResponseBase deleteRole(String value) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteRole(value));
    }
}
