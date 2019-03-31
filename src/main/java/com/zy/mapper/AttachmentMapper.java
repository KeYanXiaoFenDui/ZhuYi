package com.zy.mapper;

import com.zy.domain.Attachment;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AttachmentMapper {

    @Insert("INSERT into attachment (file_type,object_type,object_id,file_name,request_url,description,create_time,status) values (#{fileType},#{objectType},#{objectId},#{fileName},#{requestUrl},#{description},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertAttachment(Attachment attachment);

    @Update("UPDATE attachment SET file_type=#{fileType},object_type=#{objectType},object_id=#{objectId},file_name=#{fileName},request_url=#{requestUrl},description=#{description},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateAttachment(Attachment attachment);

    @Delete("delete from attachment where id=#{id}")
    public int deleteAttachment(int id);

    @Select("select * from attachment where id=#{id}")
    public Attachment getAttachment(int id);

}