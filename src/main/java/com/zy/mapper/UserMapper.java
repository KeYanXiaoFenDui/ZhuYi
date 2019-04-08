package com.zy.mapper;

import com.zy.domain.User;
import com.zy.mapper.sqlProvide.UserSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Insert("INSERT into user (acc_status,account,password,user_name,telephone,mail,create_time,update_time,status) values (#{accStatus},#{account},#{password},#{userName},#{telephone},#{mail},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertUser(User user);

    @Update("UPDATE user SET acc_status=#{accStatus},account=#{account},password=#{password},user_name=#{userName},telephone=#{telephone},mail=#{mail},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateUser(User user);

    @Delete("delete from user where id=#{id}")
    public int deleteUser(int id);

    @Select("select * from user where id=#{id}")
    public User getUser(int id);

//    @SelectProvider(type = UserSQLProvider.class,method = "getUserList")
//    public List<Map> getUserList(String name);

    @SelectProvider(type = UserSQLProvider.class,method = "getUserList")
    public List<User> getUserList(String nameOrAccount,String startTime,String endTime);

    @Update("update user set update_time = sysdate(),`status` = 0 where id = #{id}")
    public int disableUser(int id);

}