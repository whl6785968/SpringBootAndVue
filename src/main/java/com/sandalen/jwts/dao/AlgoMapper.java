package com.sandalen.jwts.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AlgoMapper {
    int staticsErrorDataPoint();
}
