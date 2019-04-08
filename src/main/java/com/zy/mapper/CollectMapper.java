package com.zy.mapper;

import com.zy.domain.Collect;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CollectMapper {

    @Insert("INSERT into collect (title,create_time,update_time,status,user_id,scenario_id,user_stage_type_id,stage_id) values (#{title},#{createTime},#{updateTime},#{status},#{userId},#{scenarioId},#{userStageTypeId},#{stageId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCollect(Collect collect);

    @Update("UPDATE collect SET title=#{title},create_time=#{createTime},update_time=#{updateTime},status=#{status},user_id=#{userId},scenario_id=#{scenarioId},user_stage_type_id=#{userStageTypeId},stage_id=#{stageId} where id=#{id}")
    public int updateCollect(Collect collect);

    @Delete("delete from collect where id=#{id}")
    public int deleteCollect(int id);

    @Select("select * from collect where id=#{id}")
    public Collect getCollect(int id);

    @Delete("delete from collect where user_id=#{userId} and scenario_id = #{scenarioId} and user_stage_type_id=#{userStageTypeId} and stage_id=#{stageId} ")
    public int deleteCollect(int userId, int scenarioId, int userStageTypeId, int stageId);

    @Delete("delete from collect where user_id=#{userId} and scenario_id = #{scenarioId} and user_stage_type_id=#{userStageTypeId} ")
    public int deleteCollect(int userId, int scenarioId, int userStageTypeId);

    @Delete("delete from collect where user_id=#{userId} and scenario_id = #{scenarioId} ")
    public int deleteCollect(int userId, int scenarioId);
}