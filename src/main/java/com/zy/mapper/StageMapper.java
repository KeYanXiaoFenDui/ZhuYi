package com.zy.mapper;

import com.zy.domain.Stage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StageMapper {

    @Insert("INSERT into stage (create_time,status,name,country,province,city,address,film_type_id,stage_main_type_id,stage_sub_type_id,film_style_id,stage_area,work_time,parking,voltage,other_note,stage_desc,pic_url,video_url,file_url,response_info_id,process_status,user_id,nameEn,addressEn,otherNoteEn,stageDescEn) values (#{createTime},#{status},#{name},#{country},#{province},#{city},#{address},#{filmTypeId},#{stageMainTypeId},#{stageSubTypeId},#{filmStyleId},#{stageArea},#{workTime},#{parking},#{voltage},#{otherNote},#{stageDesc},#{picUrl},#{videoUrl},#{fileUrl},#{responseInfoId},#{processStatus},#{userId},#{nameEn},#{addressEn},#{otherNoteEn},#{stageDescEn})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertStage(Stage stage);

    @Update("UPDATE stage SET create_time=#{createTime},status=#{status},name=#{name},country=#{country},province=#{province},city=#{city},address=#{address},film_type_id=#{filmTypeId},stage_main_type_id=#{stageMainTypeId},stage_sub_type_id=#{stageSubTypeId},film_style_id=#{filmStyleId},stage_area=#{stageArea},work_time=#{workTime},parking=#{parking},voltage=#{voltage},other_note=#{otherNote},stage_desc=#{stageDesc},pic_url=#{picUrl},video_url=#{videoUrl},file_url=#{fileUrl},response_info_id=#{responseInfoId},process_status=#{processStatus},user_id=#{userId},nameEn=#{nameEn},addressEn=#{addressEn},otherNoteEn=#{otherNoteEn},stageDescEn=#{stageDescEn} where id=#{id}")
    public int updateStage(Stage stage);

    @Delete("delete from stage where id=#{id}")
    public int deleteStage(int id);

    @Select("select * from stage where id=#{id}")
    public Stage getStage(int id);

}