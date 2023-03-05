package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_rep;

@Mapper
public interface RepMapper {
    Collection<TRN_rep> findAll();

    TRN_rep findOne(Integer rep_id);
    
    Collection<TRN_rep> outputRepList(Integer team_id, String rep_status);

    void save(TRN_rep rep);

    void update(TRN_rep rep);

    void delete(Integer rep_id);
}
