package com.jsen.test.utils.modelcore.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsen.test.utils.modelcore.ModuleColumnFilter;
import com.jsen.test.utils.modelcore.common.CalcType;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.ModelType;
import com.jsen.test.utils.modelcore.common.logical.CalcBuilder;
import com.jsen.test.utils.modelcore.common.logical.builder.AbstractBuild;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.help.Finder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Data
@Accessors(chain = true)
public class Node {
    private String uuid;

    /**
     * 模型类型， 这里直接去掉Union类型的模型由Calc节点直接连接DataSource节点
     */
    ModelType modelType = ModelType.DataSource;
    /**
     * 如果是计算节点，应该有一个计算节点类型，来确保如何解析sql
     * 实际上Union节点也会继承这个属性，但一般可以直接把Union节点去掉
     */
    CalcType calcType = CalcType.ModeAggregation;


    /**
     * Calc的父节点
     */
    List<Node> parents;

    /**
     * Calc DataSource的子节点， 这里如果在模型中子节点是Union的会直接转换到对应的Calc节点
     */
    Node child;

    boolean bad = false;

    // work conf

    // 对于计算节点 使用这个来gen sql

    CalcModeBase calcModeBase;
    String tableName;
    JSONArray tableFilter;



    // some meta conf

    // dynamic create
    // 以下的数据会在执行时动态解析生成

    /**
     * 中间步骤创建视图名字
     */
    String viewTableName;
    /**
     * 该Node的执行SQL 执行sql不包含CREATE VIEW 语句，单纯只是数据库执行并返回数据，在需要视图的时候 返回视图SQL
     */
    String sql;

    public String genViewSQL() {
        return "CREATE VIEW " + viewTableName + " AS " + sql;
    }


    /**
     * 循环遍历
     * 首先完全按照模型创建树，最后shrink所有Union节点
    */
    public Node parseSelf(Finder finder, JSONObject tableFilters, JSONArray datas, JSONArray unionDatas, JSONArray links, JSONObject selfModel) {
        if(selfModel == null) {
            this.setBad(true);
            return this;
        }
        JSONObject workConf = finder.findWorkCOnf(selfModel);
        if(workConf == null) {
            this.setBad(true);
            return this;
        }
        this.modelType = ModelType.valueOf(selfModel.getString(ConfKeys.type));
        if (modelType == ModelType.Calc) {
            String ct = workConf.getString(ConfKeys.type);
            CalcType calcType = CalcType.valueOf(ct);
            AbstractBuild build = CalcBuilder.getBuild(calcType);
            if (build == null) {
                this.setBad(true);
                return this;
            }
            this.setCalcModeBase(build.build(workConf));
        }
        this.uuid = selfModel.getString(ConfKeys.id);
        this.tableName = workConf.getString(ConfKeys.tableName);
        this.tableFilter = tableFilters.getJSONArray(this.tableName);

        // parse parent

        List<JSONObject> parents = finder.findParents(datas, unionDatas, links, this.uuid);
        if (parents == null) {
            return this;
        }
        List<Node> pcs = Lists.newArrayList();
        Node n;
        for (JSONObject parent: parents) {
            n = new Node().parseSelf(finder, tableFilters, datas, unionDatas, links, parent);
            if(!n.isBad()) {
                n.setChild(this);
                pcs.add(n);
            }
        }
        this.setParents(pcs);
        return this;
    }

    /**
     * 去除所有Union节点
     * @return
     */
    public Node shrink() {
        if(ModelType.Union.equals(getModelType())) {
            if(child != null) {
                child.parents = parents;
                if (parents != null) {
                    for (Node parent:parents) {
                        parent.child = child;
                        parent.shrink();
                    }
                }
                return child;
            }
            return null;
        } else {
            if (parents != null) {
                for (Node parent:parents) {
                    parent.shrink();
                }
            }
            return this;
        }
    }

    /**
     * 此处执行时，应该所有节点只会是DataSource或者Calc
     */
    public void genDynamicAttrs() {
        if (parents != null) {
            for (Node parent: parents) {
                parent.genDynamicAttrs();
            }
        }
        if (ModelType.DataSource == modelType) {
            String filterSQL = "";
            if (getTableFilter() != null) {
                String q = ModuleColumnFilter.parseFilterByTable(getTableFilter());
                if (q.trim().length() > 0) {
                    filterSQL = " WHERE " + q;
                }
            }
            String sql = "SELECT * FROM " + getTableName() + filterSQL + " LIMIT 0,20";
            setSql(sql);
            setViewTableName(UUID.randomUUID().toString().replaceAll("-", ""));
        } else if (ModelType.Calc == modelType) {
            if (calcModeBase != null) {
                setSql(calcModeBase.genSQL(this));
                setViewTableName(UUID.randomUUID().toString().replaceAll("-", ""));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (parents != null) {
            for (Node parent:parents) {
                builder.append(parent);
            }
        }
        builder.append("type: " + this.getModelType() + "\ntableName: " + this.getTableName() + "\nSQL: " + this.getSql() + "\nviewTableSQL: " + genViewSQL() + "\n");
        return builder.toString();
    }

    public void dumpSqlWorkFlow(List<Node> workFlow) {
        if (parents != null) {
            for (Node parent:parents) {
                parent.dumpSqlWorkFlow(workFlow);
            }
        }
        workFlow.add(this);
    }

}
