package com.zy.mapper;

import com.github.pagehelper.Page;
import com.zy.domain.Category;
import com.zy.mapper.sqlProvide.CategorySQLProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT into category (create_time,`status`,`name`,`order`,level,parent_id,`type`,nameEn) values (#{createTime},#{status},#{name},#{order},#{level},#{parentId},#{type},#{nameEn})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCategory(Category category);

    @Update("UPDATE category SET create_time=#{createTime},`status`=#{status},`name`=#{name},`order`=#{order},level=#{level},parent_id=#{parentId},`type`=#{type},nameEn=#{nameEn} where id=#{id}")
    public int updateCategory(Category category);

    @Delete("delete from category where id=#{id}")
    public int deleteCategory(int id);

    @Select("select * from category where id=#{id}")
    public Category getCategory(int id);

//    @Select("select * from category t where ((t.name like '%'||#{name}||'%') or (t.nameEn like '%'||#{name}||'%')) and t.level = #{level} and t.status = 1 order by t.order")
    @SelectProvider(type = CategorySQLProvide.class,method = "findByNameLike")
    public List<Category> findByNameLike(String name,int type,int level);

    @Select("select * from category t where t.level = #{level} and t.type = #{type} and t.status = 1 order by t.order asc")
    public List<Category> findByLevelAndType(@Param("level") int level,@Param("type")int type);

    @Update("update category set status = 0 where (id=#{id} or parent_id = #{id})")
    public int cascadeDeleteCategory(@Param("id")int id);

    @SelectProvider(type = CategorySQLProvide.class,method = "findSubByNameLike")
    public List<Category> findSubByNameLike(String parentId, String name,int type);

    @Select("select * from category c where c.`status` = 1 and c.level = 1 order by c.order ,c.create_time")
    public List<Category> getStage1stCategory();

    @Select("select * from category c where c.`status` = 1 and c.parent_id = #{id} order by c.order ,c.create_time")
    public List<Category> getSubCategoryList(int id);

    @Select("select c.id,c.`name` as dataName from category c where c.`status` = 1")
    public List<Map> getCategoryMsg();
}