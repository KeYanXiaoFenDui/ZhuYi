package com.zy.mapper;

import com.zy.domain.ResponseInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ResponseInfoMapper {

    @Insert("INSERT into response_info (create_time,status,content,type,object_id,user_id) values (#{createTime},#{status},#{content},#{type},#{objectId},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertResponseInfo(ResponseInfo responseInfo);

    @Update("UPDATE response_info SET create_time=#{createTime},status=#{status},content=#{content},type=#{type},object_id=#{objectId},user_id=#{userId} where id=#{id}")
    public int updateResponseInfo(ResponseInfo responseInfo);

    @Delete("delete from response_info where id=#{id}")
    public int deleteResponseInfo(int id);

    @Select("select * from response_info where id=#{id}")
    public ResponseInfo getResponseInfo(int id);

}