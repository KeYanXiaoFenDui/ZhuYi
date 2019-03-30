package com.zy.service;

import com.zy.domain.Attachment;
import org.springframework.stereotype.Service;

@Service
public interface IAttachmentService {
    public int insertAttachment(Attachment attachment);

    public int updateAttachment(Attachment attachment);

    public int deleteAttachment(int id);

    public Attachment getAttachment(int id);
}