package com.neuq.flight.grab.mappers;

import com.neuq.flight.grab.domain.SearchResult;
import com.neuq.flight.grab.domain.SearchResultExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Mapper
public interface SearchResultMapper {
    int countByExample(SearchResultExample example);

    int deleteByExample(SearchResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SearchResult record);

    int insertSelective(SearchResult record);

    List<SearchResult> selectByExampleWithBLOBs(SearchResultExample example);

    List<SearchResult> selectByExample(SearchResultExample example);

    SearchResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SearchResult record, @Param("example") SearchResultExample example);

    int updateByExampleWithBLOBs(@Param("record") SearchResult record, @Param("example") SearchResultExample example);

    int updateByExample(@Param("record") SearchResult record, @Param("example") SearchResultExample example);

    int updateByPrimaryKeySelective(SearchResult record);

    int updateByPrimaryKeyWithBLOBs(SearchResult record);

    int updateByPrimaryKey(SearchResult record);
}