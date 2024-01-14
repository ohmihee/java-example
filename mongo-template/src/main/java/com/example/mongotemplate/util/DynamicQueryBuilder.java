package com.example.mongotemplate.util;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

public class DynamicQueryBuilder {
    private Query query;
    public DynamicQueryBuilder() {
        this.query = new Query();
    }
    public DynamicQueryBuilder addCriteriaWithIs(String field, Object value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).is(value));
        }
        return this;
    }

    /**
     * 두 번째 인자로 i를 준 경우에는 값이 대소문자까지 일치하는 데이터를 반환하고,
     * 두 번째 인자로 i를 안 준 경우에는 값의 대소문자를 무시하고 값 그 자체에 맞는 데이터를 반환한다.
     * */
    public DynamicQueryBuilder addCriteriaWithRegex(String field, String value) {
        if (value != null & !StringUtils.isEmpty(value)){
            query.addCriteria(Criteria.where(field).regex(value,"i"));
        }
        return this;
    }

    public DynamicQueryBuilder withBySort(String field, Sort.Direction sort) {
        /**
         * 대소문자 상관없이 정렬하는 경우
         * */
        query.with(Sort.by(new Sort.Order(sort, field).ignoreCase()));
        /**
         * 대소문자도 적용하여 정렬하는 경우
         * query.with(Sort.by(Sort.Direction.ASC, field));
         * */
        return this;
    }

    public DynamicQueryBuilder addCriteriaWithGt(String field, Object value) {
        if (value != null){
            query.addCriteria(Criteria.where("field").gt(value));
        }
        return this;
    }


    public Query build () {
        return query;
    }
}
