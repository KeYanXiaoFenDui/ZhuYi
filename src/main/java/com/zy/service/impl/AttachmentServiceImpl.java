package com.zy.service.impl;

import com.zy.domain.Attachment;
import com.zy.mapper.AttachmentMapper;
import com.zy.service.IAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements IAttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public int insertAttachment(Attachment attachment) {
        return attachmentMapper.insertAttachment(attachment);
    }

    @Override
    public int updateAttachment(Attachment attachment) {
        return attachmentMapper.updateAttachment(attachment);
    }

    @Override
    public int deleteAttachment(int id) {
        return attachmentMapper.deleteAttachment(id);
    }

    @Override
    public Attachment getAttachment(int id) {
        return attachmentMapper.getAttachment(id);
    }
}