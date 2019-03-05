package com.zy.service.impl;

import com.zy.domain.OperateLog;
import com.zy.mapper.OperateLogMapper;
import com.zy.service.IOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperateLogServiceImpl implements IOperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public int insertOperateLog(OperateLog operateLog) {
        return operateLogMapper.insertOperateLog(operateLog);
    }

    @Override
    public int updateOperateLog(OperateLog operateLog) {
        return operateLogMapper.updateOperateLog(operateLog);
    }

    @Override
    public int deleteOperateLog(int id) {
        return operateLogMapper.deleteOperateLog(id);
    }

    @Override
    public OperateLog getOperateLog(int id) {
        return operateLogMapper.getOperateLog(id);
    }
}