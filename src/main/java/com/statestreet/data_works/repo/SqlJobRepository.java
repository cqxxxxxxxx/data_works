package com.statestreet.data_works.repo;

import com.statestreet.data_works.model.entity.SqlJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface SqlJobRepository extends JpaRepository<SqlJobEntity, Long> {
}
