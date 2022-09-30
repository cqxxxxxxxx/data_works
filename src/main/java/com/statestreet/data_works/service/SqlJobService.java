package com.statestreet.data_works.service;

import com.statestreet.data_works.manager.SqlJobManager;
import com.statestreet.data_works.model.dto.sqljob.SqlJobCreateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobStatusUpdateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobUpdateDTO;
import com.statestreet.data_works.model.entity.SqlJobEntity;
import com.statestreet.data_works.model.vo.SqlJobVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqlJobService implements ISqlJobService {

    private final SqlJobManager sqlJobManager;


    public SqlJobService(SqlJobManager sqlJobManager) {
        this.sqlJobManager = sqlJobManager;
    }

    @Override
    public SqlJobVO create(SqlJobCreateDTO createDTO) {
        SqlJobEntity entity = new SqlJobEntity();
        BeanUtils.copyProperties(createDTO, entity);
        SqlJobEntity sqlJobEntity = sqlJobManager.create(entity);
        return map(sqlJobEntity);
    }

    @Override
    public SqlJobVO update(SqlJobUpdateDTO updateDTO) {
        SqlJobEntity entity = new SqlJobEntity();
        BeanUtils.copyProperties(updateDTO, entity);
        SqlJobEntity sqlJobEntity = sqlJobManager.update(entity);
        return map(sqlJobEntity);
    }

    @Override
    public SqlJobVO statusUpdate(SqlJobStatusUpdateDTO updateDTO) {
        SqlJobEntity entity = new SqlJobEntity();
        BeanUtils.copyProperties(updateDTO, entity);
        SqlJobEntity sqlJobEntity = sqlJobManager.update(entity);
        return map(sqlJobEntity);
    }

    @Override
    public void delete(Long id) {
        sqlJobManager.delete(id);
    }

    private SqlJobVO map(SqlJobEntity entity) {
        SqlJobVO vo = new SqlJobVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
