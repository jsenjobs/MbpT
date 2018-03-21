package com.jsen.test.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jsen.test.entity.Testtable;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
public interface TesttableMapper extends BaseMapper<Testtable> {
    List<Testtable> selectMy(RowBounds rowBounds, @Param("ew")Wrapper<Testtable> wrapper);

    List<Testtable> listAll();
}
