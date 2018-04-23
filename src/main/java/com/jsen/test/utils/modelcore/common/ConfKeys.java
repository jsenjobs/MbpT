package com.jsen.test.utils.modelcore.common;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public interface ConfKeys {
    String datas = "datas";
    String unionDatas = "unionDatas";
    String links = "links";
    String TABLE_FILTERS = "tableFilters";

    String id = "id";
    String source = "source";
    String target = "target";
    String workConf = "_workConf";
    String type = "type";

    // work conf
    String tableName = "tableName";

    // ModeAggregation
    String calFunc = "calFunc";
    String columns = "columns";
    String funcs = "funcs";
    String keys = "keys";
    String names = "names";
    String resultCollectionName = "resultCollectionName";

    String groupColumns = "groupColumns";
    String notGroupColumns = "notGroupColumns";
    String useNotGroupColumn = "useNotGroupColumn";
}
