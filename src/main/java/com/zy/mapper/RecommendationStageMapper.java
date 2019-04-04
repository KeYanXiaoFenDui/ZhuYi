package com.zy.mapper;

import com.zy.domain.RecommendationStage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RecommendationStageMapper {

    @Insert("INSERT into recommendation_stage (rec_stage_type_id,stage_id,create_time,operate_admin_id,status) values (#{recStageTypeId},#{stageId},#{createTime},#{operateAdminId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRecommendationStage(RecommendationStage recommendationStage);

    @Update("UPDATE recommendation_stage SET rec_stage_type_id=#{recStageTypeId},stage_id=#{stageId},create_time=#{createTime},operate_admin_id=#{operateAdminId},status=#{status} where id=#{id}")
    public int updateRecommendationStage(RecommendationStage recommendationStage);

    @Update("UPDATE recommendation_stage SET operate_admin_id=#{operateAdminId},status=0 where rec_stage_type_id=#{id}")
    public int updateRecommendationStageCase(@Param("operateAdminId") int operateAdminId,@Param("id") int id);

    @Delete("delete from recommendation_stage where id=#{id}")
    public int deleteRecommendationStage(int id);

    @Select("select * from recommendation_stage where id=#{id}")
    public RecommendationStage getRecommendationStage(int id);

}