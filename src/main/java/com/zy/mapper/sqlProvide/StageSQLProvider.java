package com.zy.mapper.sqlProvide;


import com.zy.domain.vo.StageRequestVo;

public class StageSQLProvider {
//    public String getUserList(String name){
//        StringBuffer sql = new StringBuffer();
//        sql.append("select * from user where 1=1 ");
//        if(!name.equals("")){
//            sql.append(" and "+name);
//        }
//        return sql.toString();
//    }

    public String getUserStageList(int userId,int processStatus,String idOrName){
        StringBuffer sql = new StringBuffer();
        sql.append("select s.id,s.name,u.user_name as userName,u.telephone,u.mail,case when s.process_status = 0 then '已驳回' when s.process_status = 1 then '待审核' when s.process_status = 2 then '已通过' else '未知状态' end as processStatus from stage s,user u where s.user_id = u.id and 1=1 and s.`status` = 1");
        if(userId != -500){
            sql.append(" and s.user_id = "+userId);
        }
        if(processStatus != -500){
            sql.append(" and s.process_status = "+processStatus);
        }
        if(!idOrName.equals("")){
            sql.append(" and (s.id = "+idOrName+" or s.name like '%"+idOrName+"%')");
        }
        sql.append(" order by s.create_time desc");
        return sql.toString();
    }
    public String getStageList(StageRequestVo vo){
        System.out.println("vo::"+vo.toString());
        StringBuffer sql = new StringBuffer();
        sql.append("select s.id,s.`name`,s.nameEn,s.country,s.province,s.city,s.film_type_id,s.stage_main_type_id,s.stage_sub_type_id,s.stage_style_id from stage s where s.`status` = 1");
        if(vo.getCountry() != 0){
            sql.append(" and s.country = "+vo.getCountry());
        }
        if(vo.getProvince() != 0){
            sql.append(" and s.province = "+vo.getProvince());
        }
        if(vo.getCity() != 0){
            sql.append(" and s.city = "+vo.getCity());
        }
        if(vo.getFilmTypeId() != 0){
            sql.append(" and s.film_type_id = "+vo.getFilmTypeId());
        }
        if(vo.getStageMainTypeId() != 0){
            sql.append(" and s.stage_main_type_id = "+vo.getStageMainTypeId());
        }
        if(vo.getStageSubTypeId() != 0){
            sql.append(" and s.stage_sub_type_id = "+vo.getStageSubTypeId());
        }
        if(vo.getStageStyleId() != 0){
            sql.append(" and s.stage_style_id = "+vo.getStageStyleId());
        }
        if(vo.getIdOrName() != null && !vo.getIdOrName().equals("")){
            sql.append(" and (s.id = '"+vo.getIdOrName()+"' or s.`name` like '%"+vo.getIdOrName()+"%')");
        }
        if(vo.getProcessStatus() != -500){
            sql.append(" and s.process_status = "+vo.getProcessStatus());
        }
        sql.append(" order by s.create_time desc");

        System.out.println("sqlstageList::"+sql.toString());
        return sql.toString();
    }
}
