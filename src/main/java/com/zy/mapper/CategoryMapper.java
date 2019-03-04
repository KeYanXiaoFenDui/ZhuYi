package com.zy.mapper;

import com.zy.domain.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT into category (create_time,status,name,order,level,parent_id,type) values (#{createTime},#{status},#{name},#{order},#{level},#{parentId},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCategory(Category category);

    @Update("UPDATE category SET create_time=#{createTime},status=#{status},name=#{name},order=#{order},level=#{level},parent_id=#{parentId},type=#{type} where id=#{id}")
    public int updateCategory(Category category);

    @Delete("delete from category where id=#{id}")
    public int deleteCategory(int id);

    @Select("select * from category where id=#{id}")
    public Category getCategory(int id);

}