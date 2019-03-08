package com.zy.mapper.sqlProvide;


public class UserSQLProvider {
    public String getUserList(String name){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from user where 1=1 ");
        if(!name.equals("")){
            sql.append(" and "+name);
        }
        return sql.toString();
    }
}
