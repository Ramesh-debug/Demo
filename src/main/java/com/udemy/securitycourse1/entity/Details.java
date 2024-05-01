package com.udemy.securitycourse1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@Entity
@Data
@Table
public class Details
{
    @Id
    private int id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String roles;
}
