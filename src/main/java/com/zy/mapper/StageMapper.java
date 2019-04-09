package com.zy.mapper;

import com.zy.domain.Stage;
import com.zy.domain.User;
import com.zy.domain.vo.StageAuditVo;
import com.zy.domain.vo.StageRequestVo;
import com.zy.mapper.sqlProvide.StageSQLProvider;
import com.zy.mapper.sqlProvide.UserSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StageMapper {

    @Insert("INSERT into stage (create_time,status,name,country,province,city,address,film_type_id,stage_main_type_id,stage_sub_type_id,stage_style_id,stage_area,work_time,parking,voltage,other_note,stage_desc,pic_url,video_url,file_url,response_info_id,process_status,user_id,nameEn,addressEn,otherNoteEn,stageDescEn) values (#{createTime},#{status},#{name},#{country},#{province},#{city},#{address},#{filmTypeId},#{stageMainTypeId},#{stageSubTypeId},#{stageStyleId},#{stageArea},#{workTime},#{parking},#{voltage},#{otherNote},#{stageDesc},#{picUrl},#{videoUrl},#{fileUrl},#{responseInfoId},#{processStatus},#{userId},#{nameEn},#{addressEn},#{otherNoteEn},#{stageDescEn})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertStage(Stage stage);

    @Update("UPDATE stage SET create_time=#{createTime},status=#{status},name=#{name},country=#{country},province=#{province},city=#{city},address=#{address},film_type_id=#{filmTypeId},stage_main_type_id=#{stageMainTypeId},stage_sub_type_id=#{stageSubTypeId},stage_style_id=#{stageStyleId},stage_area=#{stageArea},work_time=#{workTime},parking=#{parking},voltage=#{voltage},other_note=#{otherNote},stage_desc=#{stageDesc},pic_url=#{picUrl},video_url=#{videoUrl},file_url=#{fileUrl},response_info_id=#{responseInfoId},process_status=#{processStatus},user_id=#{userId},nameEn=#{nameEn},addressEn=#{addressEn},otherNoteEn=#{otherNoteEn},stageDescEn=#{stageDescEn} where id=#{id}")
    public int updateStage(Stage stage);

    @Delete("delete from stage where id=#{id}")
    public int deleteStage(int id);

    @Select("select * from stage where id=#{id}")
    public Stage getStage(int id);

    @Select("select s.*,u.user_name,u.telephone,u.mail from stage s,user u where s.user_id = u.id and s.id=#{id} and s.`status` = 1")
    public StageAuditVo getStageAudit(int id);

    @SelectProvider(type = StageSQLProvider.class,method = "getUserStageList")
    public List<Map> getUserStageList(int userId, int processStatus, String idOrName);

    @SelectProvider(type = StageSQLProvider.class,method = "getStageList")
    public List<Stage> getStageList(StageRequestVo vo);

    @Select("select * from stage s where s.user_id = #{userId} and s.process_status = #{processStatus} and s.`status` = 1")
    public List<Stage> myStage(@Param("userId") int userId,@Param("processStatus") int processStatus);
}