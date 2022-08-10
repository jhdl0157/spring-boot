package com.ll.exam.sbb.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record QuestionDto(String subject, String content, LocalDateTime createDate)
        implements Serializable {
    public static QuestionDto of(String subject, String content, LocalDateTime createDate) {
        return new QuestionDto(subject, content, createDate);
    }
}
