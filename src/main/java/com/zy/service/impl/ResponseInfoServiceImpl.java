package com.zy.service.impl;

import com.zy.domain.ResponseInfo;
import com.zy.mapper.ResponseInfoMapper;
import com.zy.service.IResponseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseInfoServiceImpl implements IResponseInfoService {
    @Autowired
    private ResponseInfoMapper responseInfoMapper;

    @Override
    public int insertResponseInfo(ResponseInfo responseInfo) {
        return responseInfoMapper.insertResponseInfo(responseInfo);
    }

    @Override
    public int updateResponseInfo(ResponseInfo responseInfo) {
        return responseInfoMapper.updateResponseInfo(responseInfo);
    }

    @Override
    public int deleteResponseInfo(int id) {
        return responseInfoMapper.deleteResponseInfo(id);
    }

    @Override
    public ResponseInfo getResponseInfo(int id) {
        return responseInfoMapper.getResponseInfo(id);
    }
}