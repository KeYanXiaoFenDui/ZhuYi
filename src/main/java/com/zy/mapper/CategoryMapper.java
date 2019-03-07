package com.zy.mapper;

import com.github.pagehelper.Page;
import com.zy.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT into category (create_time,status,name,order,level,parent_id,type,nameEn) values (#{createTime},#{status},#{name},#{order},#{level},#{parentId},#{type},#{nameEn})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCategory(Category category);

    @Update("UPDATE category SET create_time=#{createTime},status=#{status},name=#{name},order=#{order},level=#{level},parent_id=#{parentId},type=#{type},nameEn=#{nameEn} where id=#{id}")
    public int updateCategory(Category category);

    @Delete("delete from category where id=#{id}")
    public int deleteCategory(int id);

    @Select("select * from category where id=#{id}")
    public Category getCategory(int id);

    @Select("select * from category t where ((t.name like '%'||#{name}||'%') or (t.nameEn like '%'||#{name}||'%')) and t.level = #{level} and t.status = 1 order by t.order")
    public List<Category> findByNameLike(@Param("name")String name,@Param("type")int type,@Param("level")int level);

    @Select("select * from category t where t.level = #{level} and t.type = #{type} and t.status = 1 order by t.order asc")
    public List<Category> findByLevelAndType(@Param("level") int level,@Param("type")int type);
}