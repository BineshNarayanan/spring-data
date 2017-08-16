package com.ai.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"employee"})
@NoArgsConstructor
@Entity(name = "EMP_CONTACT_DETAILS")
public class EmployeeContactDetails {

    @Id
    @Column(name="ID")
    private String id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    @Column(name="CONTACT_TYPE")
    private String contactType;

    @Column(name="CONTACT_NUMBER")
    private String contactNumber;

    @Column(name="STATUS")
    private int status;

    @PrePersist
    public void preCreate(){
        if(StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
        if(StringUtils.isEmpty(id)) {
            this.status = 1;
        }
    }
}
