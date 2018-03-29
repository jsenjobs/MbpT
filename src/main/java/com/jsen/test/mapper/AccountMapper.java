package com.jsen.test.mapper;

import com.jsen.test.entity.Account;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.union.AccountJoinWeibos;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
@Service
public interface AccountMapper extends BaseMapper<Account> {
    List<AccountJoinWeibos> listAccountJoinWeibos();
}
