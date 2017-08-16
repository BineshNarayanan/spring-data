package com.ai.training.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMP_STATUS")
    private int empStatus;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeContactDetails> contactDetails;

    @PrePersist
    public void preCreate(){
        if(StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
        if(StringUtils.isEmpty(id)) {
            this.status = 1;
        }
        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
    }

    @PreUpdate
    public void preUpdate(){
        Date date = new Date();
        this.updateTime = date;
    }
}
