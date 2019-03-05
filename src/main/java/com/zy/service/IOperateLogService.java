package com.zy.service;

import com.zy.domain.OperateLog;
import org.springframework.stereotype.Service;

@Service
public interface IOperateLogService {
    public int insertOperateLog(OperateLog operateLog);

    public int updateOperateLog(OperateLog operateLog);

    public int deleteOperateLog(int id);

    public OperateLog getOperateLog(int id);
}