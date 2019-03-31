package com.zy.mapper.sqlProvide;


public class UserRequestSQLProvider {
//    public String getUserList(String name){
//        StringBuffer sql = new StringBuffer();
//        sql.append("select * from user where 1=1 ");
//        if(!name.equals("")){
//            sql.append(" and "+name);
//        }
//        return sql.toString();
//    }

    public String getUserRequestList(int id,int reqStatus, String filmName,String startTime,String endTime){
        StringBuffer sql = new StringBuffer();
        sql.append("select ur.id,ur.film_name as filmName,ur.contact_people as contactPeople,ur.telephone,ur.create_time as createTime,case when ur.req_status = 0 then '已终止' when ur.req_status = 1 then '待推荐' when ur.req_status = 2 then '推荐中' else '未定义状态' end as reqStatus from user_request ur where 1=1 and ur.`status` = 1");
        if(id != -500){
            sql.append(" and ur.user_id = "+id);
        }
        if(reqStatus != -500){
            sql.append(" and ur.req_status = "+reqStatus);
        }
        if(!filmName.equals("")){
            sql.append(" and ur.film_name like '%"+filmName+"%'");
        }
        if(!startTime.equals("")){
            sql.append(" and ur.create_time > str_to_date('"+startTime+"','%Y-%m-%d')");
        }
        if(!endTime.equals("")){
            sql.append(" and ur.create_time < str_to_date('"+endTime+"','%Y-%m-%d')");
        }
        sql.append(" order by ur.create_time desc");
        return sql.toString();
    }
}
