package com.jsen.test;

import com.jsen.test.service.impl.HcModelCoreTaskServiceImpl;
import com.jsen.test.utils.modelcore.common.CalcType;
import com.jsen.test.utils.modelcore.common.logical.model.ModeAggregation;
import org.junit.Test;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
public class EnumTest {
    @Test
    public void testEnumValue() {
        HcModelCoreTaskServiceImpl.MODEL_TYPE mt = HcModelCoreTaskServiceImpl.MODEL_TYPE.DataSource;
        System.out.println("DataSource".equals(mt.toString()));

        System.out.println(ModeAggregation.Type.values()[0]);

        try {
            System.out.println(CalcType.valueOf("sdfcs"));
        } catch (Exception e) {
        }
    }
}
