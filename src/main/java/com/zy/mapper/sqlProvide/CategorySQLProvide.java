package com.zy.mapper.sqlProvide;


public class CategorySQLProvide {
    public String findByNameLike(String name,int type,int level){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from category t where t.status = 1 ");
        if(!name.equals("")){
            sql.append(" and ((t.name like '%"+name+"%') or (t.nameEn like '%"+name+"%')) ");
        }
        sql.append(" and t.level = "+level+" order by t.order");
        return sql.toString();
    }
}
