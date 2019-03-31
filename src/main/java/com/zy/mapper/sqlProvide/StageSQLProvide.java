package com.zy.mapper.sqlProvide;


import com.zy.vo.StageQueryListVo;
import org.apache.commons.lang.StringUtils;

public class StageSQLProvide {
    public String findByQueryVo(StageQueryListVo listVo){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from stage t where t.status = 1 ");
        if(StringUtils.isNotBlank(listVo.getKeyword())){
            sql.append(" and ((t.name like '%"+listVo.getKeyword()+"%') or (t.id like '%"+listVo.getKeyword()+"%')) ");
        }
        if(listVo.getStageLocaled()!=null){
            sql.append(" and t.country = "+listVo.getStageLocaled());
        }
        if(listVo.getStageSubLocaled()!=null){
            sql.append(" and t.province = "+listVo.getStageSubLocaled());
        }
        if(listVo.getFilmType()!=null){
            sql.append(" and t.filmTypeId = "+listVo.getFilmType());
        }
        if(listVo.getStageType()!=null){
            sql.append(" and t.stageMainTypeId = "+listVo.getStageType());
        }
         if(listVo.getStageSubType()!=null){
            sql.append(" and t.stageSubTypeId = "+listVo.getStageSubType());
        }
         if(listVo.getStageStyle()!=null){
            sql.append(" and t.stageType = "+listVo.getStageStyle());
        }
        return sql.toString();
    }
}
