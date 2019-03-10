package com.zy.mapper.sqlProvide;


import org.apache.commons.lang.StringUtils;

public class CategorySQLProvide {
    public String findByNameLike(String name,int type,int level){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from category t where t.status = 1 ");
        if(StringUtils.isNotBlank(name)){
            sql.append(" and ((t.name like '%"+name+"%') or (t.nameEn like '%"+name+"%')) ");
        }
        sql.append(" and t.type = "+type);
        sql.append(" and t.level = "+level+" order by t.order");
        return sql.toString();
    }
}
