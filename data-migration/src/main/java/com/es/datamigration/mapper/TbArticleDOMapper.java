package com.es.datamigration.mapper;

import com.es.datamigration.model.TbArticleDO;

import java.util.List;

public interface TbArticleDOMapper {

    /**
     * 查询所有数据列表
     * @return
     */
    List<TbArticleDO> selectAll();

}
