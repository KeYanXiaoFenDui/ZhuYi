package com.zy.mapper.sqlProvide;


public class UserSQLProvider {
//    public String getUserList(String name){
//        StringBuffer sql = new StringBuffer();
//        sql.append("select * from user where 1=1 ");
//        if(!name.equals("")){
//            sql.append(" and "+name);
//        }
//        return sql.toString();
//    }

    public String getUserList(String nameOrAccount,String startTime,String endTime){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from user u where 1=1 ");
        if(!nameOrAccount.equals("")){
            sql.append(" and (u.user_name like '%"+nameOrAccount+"%' or u.account like '%"+nameOrAccount+"%')");
        }
        if(!startTime.equals("")){
            sql.append(" and u.create_time > str_to_date('"+startTime+"','%Y-%m-%d')");
        }
        if(!endTime.equals("")){
            sql.append(" and u.create_time < str_to_date('"+endTime+"','%Y-%m-%d')");
        }
        sql.append(" order by u.update_time desc");
        return sql.toString();
    }
}
