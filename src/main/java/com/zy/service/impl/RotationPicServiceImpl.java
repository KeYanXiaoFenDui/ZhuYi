package com.zy.service.impl;

import com.zy.domain.RotationPic;
import com.zy.mapper.RotationPicMapper;
import com.zy.service.IRotationPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationPicServiceImpl implements IRotationPicService {
    @Autowired
    private RotationPicMapper rotationPicMapper;

    @Override
    public int insertRotationPic(RotationPic rotationPic) {
        return rotationPicMapper.insertRotationPic(rotationPic);
    }

    @Override
    public int updateRotationPic(RotationPic rotationPic) {
        return rotationPicMapper.updateRotationPic(rotationPic);
    }

    @Override
    public int deleteRotationPic(int id, int operaterId) {
        return rotationPicMapper.deleteRotationPic(id, operaterId);
    }


    @Override
    public RotationPic getRotationPic(int id) {
        return rotationPicMapper.getRotationPic(id);
    }

    @Override
    public List<RotationPic> getRotationPicList() {
        return rotationPicMapper.getRotationPicList();
    }
}