package com.zy.mapper;

import com.zy.domain.UserStageType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserStageTypeMapper {

    @Insert("INSERT into user_stage_type (title,create_time,update_time,status,user_id,scenario_id,type_name) values (#{title},#{createTime},#{updateTime},#{status},#{userId},#{scenarioId},#{typeName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertUserStageType(UserStageType userStageType);

    @Update("UPDATE user_stage_type SET title=#{title},create_time=#{createTime},update_time=#{updateTime},status=#{status},user_id=#{userId},scenario_id=#{scenarioId},type_name=#{typeName} where id=#{id}")
    public int updateUserStageType(UserStageType userStageType);

    @Delete("delete from user_stage_type where id=#{id}")
    public int deleteUserStageType(int id);

    @Select("select * from user_stage_type where id=#{id}")
    public UserStageType getUserStageType(int id);

    @Select("select * from user_stage_type where scenario_id=#{scenarioId}")
    public List<UserStageType> getUserStageTypeByScenarioId(int scenarioId);

    @Delete("delete from user_stage_type where scenario_id=#{scenarioId} and user_id=#{userId}")
    public int deleteUserStageTypeByScenarioId(@Param("userId") int userId, @Param("scenarioId") int scenarioId);
}