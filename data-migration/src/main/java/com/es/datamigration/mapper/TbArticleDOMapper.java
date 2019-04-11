package com.es.datamigration.mapper;

import com.es.datamigration.model.TbArticleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbArticleDOMapper {

    /**
     * 查询所有数据列表
     * @return
     */
    List<TbArticleDO> selectAll();

    /**
     * 分页查询所有数据列表
     * @return
     */
    List<TbArticleDO> selectByPage(@Param("skip") int skip, @Param("limit") int limit);

}
