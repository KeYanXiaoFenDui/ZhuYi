package com.zy.mapper;


import com.zy.domain.Role;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper {

    @Insert("INSERT into role (title,create_time,status) values (#{title},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRole(Role role);

    @Update("UPDATE role SET title=#{title},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateRole(Role role);

    @Delete("delete from role where id=#{id}")
    public int deleteRole(int id);

    @Select("select * from role where id=#{id}")
    public Role getRole(int id);

}