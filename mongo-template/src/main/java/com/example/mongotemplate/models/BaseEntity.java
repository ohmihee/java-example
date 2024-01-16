package com.example.mongotemplate.models;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
public class BaseEntity implements Serializable {
    @Id
    private String id;
    @Field("createdAt")
    private long createdAt;
    @Field("updatedAt")
    private long updatedAt;

    @Field("createdBy")
    private String createdBy;
    @Field("updatedBy")
    private String updatedBy;

    public BaseEntity() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = createdAt;
    }

    public void updated() {
        this.updatedAt = System.currentTimeMillis();
    }
}
