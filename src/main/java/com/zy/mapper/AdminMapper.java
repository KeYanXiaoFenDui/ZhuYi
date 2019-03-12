package com.zy.mapper;


import com.zy.domain.Admin;
import com.zy.mapper.sqlProvide.AdminSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert("INSERT into admin (acc_status,account,password,name,role_id,create_time,status) values (#{accStatus},#{account},#{password},#{name},#{roleId},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertAdmin(Admin admin);

    @Update("UPDATE admin SET acc_status=#{accStatus},account=#{account},password=#{password},name=#{name},role_id=#{roleId},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateAdmin(Admin admin);

    @Delete("delete from admin where id=#{id}")
    public int deleteAdmin(int id);

    @Select("select * from admin where id=#{id}")
    public Admin getAdmin(int id);

    @SelectProvider(type = AdminSQLProvider.class,method = "getAdminList")
    public List<Admin> getAdminList(String accountOrName);

}