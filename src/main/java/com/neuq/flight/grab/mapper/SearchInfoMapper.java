package com.neuq.flight.grab.mapper;

import com.neuq.flight.grab.domain.SearchInfo;
import com.neuq.flight.grab.domain.SearchInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchInfoMapper {
    int countByExample(SearchInfoExample example);

    int deleteByExample(SearchInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SearchInfo record);

    int insertSelective(SearchInfo record);

    List<SearchInfo> selectByExample(SearchInfoExample example);

    SearchInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SearchInfo record, @Param("example") SearchInfoExample example);

    int updateByExample(@Param("record") SearchInfo record, @Param("example") SearchInfoExample example);

    int updateByPrimaryKeySelective(SearchInfo record);

    int updateByPrimaryKey(SearchInfo record);
}