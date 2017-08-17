package com.ai.training.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private int empStatus;
    private int status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
}
