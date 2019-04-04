package com.zy.mapper;

import com.zy.domain.Recommendation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecommendationMapper {

    @Insert("INSERT into recommendation (user_id,req_id,recommend_status,stage_title_ch,stage_title_en,create_time,update_time,operate_admin_id,status) values (#{userId},#{reqId},#{recommendStatus},#{stageTitleCh},#{stageTitleEn},#{createTime},#{updateTime},#{operateAdminId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRecommendation(Recommendation recommendation);

    @Update("UPDATE recommendation SET user_id=#{userId},req_id=#{reqId},recommend_status=#{recommendStatus},stage_title_ch=#{stageTitleCh},stage_title_en=#{stageTitleEn},create_time=#{createTime},update_time=#{updateTime},operate_admin_id=#{operateAdminId},status=#{status} where id=#{id}")
    public int updateRecommendation(Recommendation recommendation);

    @Delete("delete from recommendation where id=#{id}")
    public int deleteRecommendation(int id);

    @Select("select * from recommendation where id=#{id}")
    public Recommendation getRecommendation(int id);

    @Select("select r.id,ur.film_name as filmName,r.update_time as updateTime,r.recommend_status as recommendStatus from recommendation r,user_request ur where r.req_id = ur.id and r.`status` = 1 and r.req_id = #{reqId} group by r.req_id order by r.update_time desc")
    public List<Map> getRecommendListById(int reqId);

    @Select("select ur.user_id as userId,r.req_id as reqId,ur.film_name as filmName,r.id,ifnull(r.stage_title_ch,'') as stageTitleCh,ifnull(r.stage_title_en,'') as stageTitleEn from recommendation r,user_request ur where r.req_id = ur.id and r.req_id = #{reqId} and r.`status` = 1")
    public List<Map> getRecommendationDetail(int reqId);

    @Select("select rs.id as rsId,s.id,s.`name`,s.country,s.province,s.city,s.film_type_id as filmTypeId,s.stage_main_type_id as stageMainTypeId,s.stage_sub_type_id as stageSubTypeId,s.stage_style_id as stageStyleId from recommendation r,recommendation_stage rs,stage s where rs.`status` = 1 and r.`status` = 1 and r.id = rs.rec_stage_type_id and s.id = rs.stage_id and r.id = #{id}")
    public List<Map> getRecommendStageList(int id);

}