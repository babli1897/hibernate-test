package jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass /*child classes will have the columns specified in this class*/
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"})
public abstract class AuditModel {

    @Column(nullable = false, updatable = false)
    @CreatedDate // if table is created via code, this is mandatory since there is no deafult value
    private Date createdAt;

    @Column(updatable = false)
    @LastModifiedDate // similar reason as createdAt
    private Date updatedAt;


    /* we have annotated createdAt and updatedAt fields with @CreatedDate and @LastModifiedDate annotations.
    what we want is that these fields should automatically get populated whenever we create or update an entity.
    To achieve this, we need to do two things -

    1. Add Spring Data JPA’s AuditingEntityListener to the domain model

    2. Enable JPA Auditing in the main application.*/
}
