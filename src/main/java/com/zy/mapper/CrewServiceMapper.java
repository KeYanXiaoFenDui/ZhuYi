package com.zy.mapper;

import com.zy.domain.CrewService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CrewServiceMapper {

    @Insert("INSERT into crew_service (create_time,`status`,`name`,`order`,service_logo_url,service_pic_url,description,nameEn,descriptionEn) values (#{createTime},#{status},#{name},#{order},#{serviceLogoUrl},#{servicePicUrl},#{description},#{nameEn},#{descriptionEn})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCrewService(CrewService crewService);

    @Update("UPDATE crew_service SET create_time=#{createTime},`status`=#{status}`name`=#{name},`order`=#{order},service_logo_url=#{serviceLogoUrl},service_pic_url=#{servicePicUrl},description=#{description},nameEn=#{nameEn},descriptionEn=#{descriptionEn} where id=#{id}")
    public int updateCrewService(CrewService crewService);

    @Delete("update crew_service set `status` = 0 where id=#{id}")
    public int deleteCrewService(@Param("id") int id);

    @Select("select * from crew_service where id=#{id}")
    public CrewService getCrewService(int id);

    @Select("select * from crew_service where `status` = 1")
    public List<CrewService> getCrewServiceList();

}