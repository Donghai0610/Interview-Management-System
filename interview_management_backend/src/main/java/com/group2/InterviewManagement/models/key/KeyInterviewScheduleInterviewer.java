package com.group2.InterviewManagement.models.key;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Embeddable
@Builder
public class KeyInterviewScheduleInterviewer implements Serializable {
    private Integer scheduleId;
    private Integer userId;
}
