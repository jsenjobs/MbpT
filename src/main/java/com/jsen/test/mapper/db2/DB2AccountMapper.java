package com.jsen.test.mapper.db2;

import com.jsen.test.entity.Account;
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
@Service("db2AccountMapper")
public interface DB2AccountMapper {
    List<Account> listAll();
}
