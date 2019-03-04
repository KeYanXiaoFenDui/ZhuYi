package com.zy.mapper;

import com.zy.domain.CrewService;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CrewServiceMapper {

    @Insert("INSERT into crew_service (create_time,status,name,order,service_logo_url,service_pic_url,description) values (#{createTime},#{status},#{name},#{order},#{serviceLogoUrl},#{servicePicUrl},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCrewService(CrewService crewService);

    @Update("UPDATE crew_service SET create_time=#{createTime},status=#{status},name=#{name},order=#{order},service_logo_url=#{serviceLogoUrl},service_pic_url=#{servicePicUrl},description=#{description} where id=#{id}")
    public int updateCrewService(CrewService crewService);

    @Delete("delete from crew_service where id=#{id}")
    public int deleteCrewService(int id);

    @Select("select * from crew_service where id=#{id}")
    public CrewService getCrewService(int id);

}