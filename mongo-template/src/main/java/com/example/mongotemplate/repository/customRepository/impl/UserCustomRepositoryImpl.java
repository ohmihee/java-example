package com.example.mongotemplate.repository.customRepository.impl;

import com.example.mongotemplate.models.User;
import com.example.mongotemplate.repository.customRepository.UserCustomRepository;
import com.example.mongotemplate.util.DynamicQueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final MongoTemplate mongoTemplate;


    @Override
    public List<User> getUserByFullNameLike(String fullName) {
        DynamicQueryBuilder builder = new DynamicQueryBuilder();
        builder.addCriteriaWithRegex("fullName", fullName);
        Query query  = builder.build();
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> getAll() {
        mongoTemplate.findAll(User.class);
        return null;
    }

    @Override
    public List<User> etUserByAgeGreaterThan(Integer age) {
        DynamicQueryBuilder builder = new DynamicQueryBuilder();
        builder.withBySort("fullName",Sort.Direction.ASC);
        builder.addCriteriaWithGt("age", age);
        Query query =  builder.build();
        //query.with(Sort.by(new Sort.Order(Sort.Direction.ASC, "fullName").ignoreCase()))
        return mongoTemplate.find(query, User.class);
    }

}
