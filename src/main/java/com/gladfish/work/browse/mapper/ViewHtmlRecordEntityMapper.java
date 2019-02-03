package com.gladfish.work.browse.mapper;

import com.gladfish.work.browse.model.ViewHtmlRecordEntity;

import java.util.List;

public interface ViewHtmlRecordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ViewHtmlRecordEntity record);

    int insertSelective(ViewHtmlRecordEntity record);

    ViewHtmlRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ViewHtmlRecordEntity record);

    int updateByPrimaryKey(ViewHtmlRecordEntity record);

    int countByViewHtmlId(Long viewHtmlId);

    List<ViewHtmlRecordEntity> listByViewHtmlId(Long viewHtmlId);
}