package com.jsen.test.service.impl;

import com.jsen.test.entity.SysUser;
import com.jsen.test.mapper.SysUserMapper;
import com.jsen.test.service.SysUserService;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public ResponseBase createUser(String name) {
        return ResponseBase.create().code(0).add("eff", baseMapper.createUser(new SysUser().setName(name).setPassword("123456")));
    }

    @Override
    public ResponseBase deleteUser(String name) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteUser(name));
    }
}
