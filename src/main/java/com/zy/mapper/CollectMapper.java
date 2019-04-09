package com.zy.mapper;

import com.zy.domain.Collect;
import com.zy.domain.vo.CollectStageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    public int deleteCollectByStageId(int userId, int scenarioId, int userStageTypeId, int stageId);

    @Delete("delete from collect where user_id=#{userId} and scenario_id = #{scenarioId} and user_stage_type_id=#{userStageTypeId} ")
    public int deleteCollectByStageTypeId(int userId, int scenarioId, int userStageTypeId);

    @Delete("delete from collect where user_id=#{userId} and scenario_id = #{scenarioId} ")
    public int deleteCollectByScenarioId(int userId, int scenarioId);

    @Select("select s.*,c.scenario_id,c.user_stage_type_id from stage s ,(select c.user_id,c.scenario_id,c.user_stage_type_id,c.stage_id from collect c where c.user_id = #{userId} and c.scenario_id = #{scenarioId} and c.`status` = 1) c where s.id = c.stage_id")
    public List<CollectStageVo> getStageForCollect(@Param("userId") int userId,@Param("scenarioId") int scenarioId);

    @Update("update collect set user_stage_type_id = #{userStageTypeId},update_time = sysdate() where id = #{collectId}")
    public int updateStageType(@Param("collectId") int collectId,@Param("userStageTypeId") int userStageTypeId);
}