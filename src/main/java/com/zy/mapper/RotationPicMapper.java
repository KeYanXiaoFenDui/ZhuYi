package com.zy.mapper;

import com.zy.domain.RotationPic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RotationPicMapper {

    @Insert("INSERT into rotation_pic (`order`,pic_name,request_url,pic_url,create_time,update_time,operater_id,status) values (#{order},#{picName},#{requestUrl},#{picUrl},#{createTime},#{updateTime},#{operaterId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRotationPic(RotationPic rotationPic);

    @Update("UPDATE rotation_pic SET `order`=#{order},pic_name=#{picName},request_url=#{requestUrl},pic_url=#{picUrl},create_time=#{createTime},update_time=#{updateTime},operater_id=#{operaterId},status=#{status} where id=#{id}")
    public int updateRotationPic(RotationPic rotationPic);

    @Delete("update rotation_pic set status = 0,update_time = sysdate(),operater_id=#{operaterId} where id=#{id}")
    public int deleteRotationPic(@Param("id") int id,@Param("operaterId") int operaterId);

    @Select("select * from rotation_pic where id=#{id}")
    public RotationPic getRotationPic(int id);

    @Select("select * from rotation_pic where `status` = 1 order by update_time desc limit 0,5")
    public List<RotationPic> getRotationPicList();

}