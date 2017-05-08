package com.neuq.flight.grab.mapper;

import com.neuq.flight.grab.domain.SearchResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchResult record);

    int insertSelective(SearchResult record);

    SearchResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchResult record);

    int updateByPrimaryKeyWithBLOBs(SearchResult record);

    int updateByPrimaryKey(SearchResult record);
}