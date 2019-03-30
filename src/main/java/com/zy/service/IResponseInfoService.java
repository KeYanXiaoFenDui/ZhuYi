package com.zy.service;

import com.zy.domain.ResponseInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IResponseInfoService {
    public int insertResponseInfo(ResponseInfo responseInfo);

    public int updateResponseInfo(ResponseInfo responseInfo);

    public int deleteResponseInfo(int id);

    public ResponseInfo getResponseInfo(int id);

    public List<ResponseInfo> getResponseInfoList();
}