package com.jsen.test.utils.modelcore.common.logical;

import com.google.common.collect.Maps;
import com.jsen.test.utils.modelcore.common.CalcType;
import com.jsen.test.utils.modelcore.common.logical.builder.AbstractBuild;
import com.jsen.test.utils.modelcore.common.logical.builder.ModeAggregationBuild;

import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public class CalcBuilder {
    private static Map<CalcType, AbstractBuild> builderMap = Maps.newHashMap();

    static {
        builderMap.put(CalcType.ModeAggregation, new ModeAggregationBuild());
    }

    public static AbstractBuild getBuild(CalcType calcType) {
        return builderMap.get(calcType);
    }
}
