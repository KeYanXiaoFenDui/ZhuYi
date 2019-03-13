package com.zy.mapper.sqlProvide;

public class AdminSQLProvider {
    public String getAdminList(String accountOrName){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from admin where 1=1 and acc_status = 1 ");
        if(!accountOrName.equals("")){
            sql.append(" and (account like '%"+accountOrName+"%' or name like '%"+accountOrName+"%')");
        }
        sql.append(" order by create_time desc,id desc");
        return sql.toString();
    }
}
