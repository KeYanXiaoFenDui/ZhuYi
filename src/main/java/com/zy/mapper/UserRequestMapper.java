package com.zy.mapper;

import com.zy.domain.User;
import com.zy.domain.UserRequest;
import com.zy.mapper.sqlProvide.UserRequestSQLProvider;
import com.zy.mapper.sqlProvide.UserSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRequestMapper {

    @Insert("INSERT into user_request (user_id,req_status,contact_people,job,telephone,mail,film_name,film_type,play_platform,country,province,city,series_type,stage_type,style,prepare_date,begin_date,continue_day,shoot_day,group_people_number,group_car_number,budget,description,director,performer,prod_company,exe_company,other_req,create_time,update_time,operate_admin_id,status) values (#{userId},#{reqStatus},#{contactPeople},#{job},#{telephone},#{mail},#{filmName},#{filmType},#{playPlatform},#{country},#{province},#{city},#{seriesType},#{stageType},#{style},#{prepareDate},#{beginDate},#{continueDay},#{shootDay},#{groupPeopleNumber},#{groupCarNumber},#{budget},#{description},#{director},#{performer},#{prodCompany},#{exeCompany},#{otherReq},#{createTime},#{updateTime},#{operateAdminId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertUserRequest(UserRequest userRequest);

    @Update("UPDATE user_request SET user_id=#{userId},req_status=#{reqStatus},contact_people=#{contactPeople},job=#{job},telephone=#{telephone},mail=#{mail},film_name=#{filmName},film_type=#{filmType},play_platform=#{playPlatform},country=#{country},province=#{province},city=#{city},series_type=#{seriesType},stage_type=#{stageType},style=#{style},prepare_date=#{prepareDate},begin_date=#{beginDate},continue_day=#{continueDay},shoot_day=#{shootDay},group_people_number=#{groupPeopleNumber},group_car_number=#{groupCarNumber},budget=#{budget},description=#{description},director=#{director},performer=#{performer},prod_company=#{prodCompany},exe_company=#{exeCompany},other_req=#{otherReq},create_time=#{createTime},update_time=#{updateTime},operate_admin_id=#{operateAdminId},status=#{status} where id=#{id}")
    public int updateUserRequest(UserRequest userRequest);

    @Delete("delete from user_request where id=#{id}")
    public int deleteUserRequest(int id);

    @Select("select * from user_request where id=#{id}")
    public UserRequest getUserRequest(int id);

    @SelectProvider(type = UserRequestSQLProvider.class,method = "getUserRequestList")
    public List<Map> getUserRequestList(int id,int reqStatus, String filmName, String startTime, String endTime);

}