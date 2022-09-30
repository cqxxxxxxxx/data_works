package com.statestreet.data_works.manager;

import com.statestreet.data_works.model.entity.SqlJobEntity;
import com.statestreet.data_works.repo.SqlJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SqlJobManager {

    private final SqlJobRepository repository;

    public SqlJobManager(SqlJobRepository repository) {
        this.repository = repository;
    }
    public SqlJobEntity create(SqlJobEntity sqlJobEntity) {
        return repository.save(sqlJobEntity);
    }

    public SqlJobEntity update(SqlJobEntity sqlJobEntity) {
        return repository.save(sqlJobEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SqlJobEntity findById(Long id) {
        return repository.findById(id).get();
    }
}
