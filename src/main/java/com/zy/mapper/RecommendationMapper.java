package com.zy.mapper;

import com.zy.domain.Recommendation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RecommendationMapper {

    @Insert("INSERT into recommendation (user_id,req_id,recommend_status,stage_type,stage_title_ch,stage_title_en,stage_id,create_time,update_time,operate_admin_id,status) values (#{userId},#{reqId},#{recommendStatus},#{stageType},#{stageTitleCh},#{stageTitleEn},#{stageId},#{createTime},#{updateTime},#{operateAdminId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRecommendation(Recommendation recommendation);

    @Update("UPDATE recommendation SET user_id=#{userId},req_id=#{reqId},recommend_status=#{recommendStatus},stage_type=#{stageType},stage_title_ch=#{stageTitleCh},stage_title_en=#{stageTitleEn},stage_id=#{stageId},create_time=#{createTime},update_time=#{updateTime},operate_admin_id=#{operateAdminId},status=#{status} where id=#{id}")
    public int updateRecommendation(Recommendation recommendation);

    @Delete("delete from recommendation where id=#{id}")
    public int deleteRecommendation(int id);

    @Select("select * from recommendation where id=#{id}")
    public Recommendation getRecommendation(int id);

}