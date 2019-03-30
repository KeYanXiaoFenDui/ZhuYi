package com.zy.service;

import com.zy.domain.RotationPic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRotationPicService {
    public int insertRotationPic(RotationPic rotationPic);

    public int updateRotationPic(RotationPic rotationPic);

    public int deleteRotationPic(int id,int operaterId);

    public RotationPic getRotationPic(int id);

    public List<RotationPic> getRotationPicList();
}