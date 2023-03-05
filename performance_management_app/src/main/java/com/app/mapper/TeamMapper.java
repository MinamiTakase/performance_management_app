package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_team;

@Mapper
public interface TeamMapper {

    Iterable<TRN_team> findAll();

    TRN_team findOne(Integer team_id);
    
    TRN_team findTeam(String team_status);
    
    Collection<TRN_team> outputTeamList(String team_status);

    void save(TRN_team team);

    void update(TRN_team team);

    void delete(Integer team_id);

}
