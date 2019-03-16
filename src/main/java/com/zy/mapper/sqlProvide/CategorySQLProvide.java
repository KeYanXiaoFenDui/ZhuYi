package com.zy.mapper.sqlProvide;


import org.apache.commons.lang.StringUtils;

public class CategorySQLProvide {
    public String findByNameLike(String name,Integer type,int level){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from category t where t.status = 1 ");
        if(StringUtils.isNotBlank(name)){
            sql.append(" and ((t.name like '%"+name+"%') or (t.nameEn like '%"+name+"%')) ");
        }
        if(type!=null) {
            sql.append(" and t.type = " + type);
        }
        sql.append(" and t.level = "+level);
        sql.append(" and t.status = 1 ");
        sql.append(" order by t.order ");
        return sql.toString();
    }

    public String findSubByNameLike(String parentId, String name,Integer type){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from category t where t.status = 1 ");
        if(StringUtils.isNotBlank(parentId)){
            sql.append(" and t.parent_id = "+parentId);
        }
        if(StringUtils.isNotBlank(name)){
            sql.append(" and ((t.name like '%"+name+"%') or (t.nameEn like '%"+name+"%')) ");
        }
        if(type!=null) {
            sql.append(" and t.type = " + type);
        }
        sql.append(" and t.status = 1 ");
        sql.append(" order by t.order ");
        return sql.toString();
    }
}
