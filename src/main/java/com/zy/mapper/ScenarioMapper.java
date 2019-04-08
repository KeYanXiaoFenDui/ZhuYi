package com.zy.mapper;

import com.zy.domain.Scenario;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScenarioMapper {

    @Insert("INSERT into scenario (title,create_time,update_time,status,user_id,scenario_name) values (#{title},#{createTime},#{updateTime},#{status},#{userId},#{scenarioName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertScenario(Scenario scenario);

    @Update("UPDATE scenario SET title=#{title},create_time=#{createTime},update_time=#{updateTime},status=#{status},user_id=#{userId},scenario_name=#{scenarioName} where id=#{id}")
    public int updateScenario(Scenario scenario);

    @Delete("delete from scenario where id=#{id}")
    public int deleteScenario(int id);

    @Select("select * from scenario where id=#{id}")
    public Scenario getScenario(int id);

    @Select("select * from scenario where user_id=#{userId}")
    public List<Scenario> getScenarioByUserId(int userId);
}