package com.statestreet.data_works.api;

import com.statestreet.data_works.model.Result;
import com.statestreet.data_works.model.constant.Constants;
import com.statestreet.data_works.model.constant.JobStatus;
import com.statestreet.data_works.model.dto.sqljob.SqlJobCreateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobStatusUpdateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobUpdateDTO;
import com.statestreet.data_works.model.vo.SqlJobVO;
import com.statestreet.data_works.service.SqlJobService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_PREFIX + "/job/sql")
public class SqlJobController {
    private final SqlJobService sqlJobService;

    public SqlJobController(SqlJobService sqlJobService) {
        this.sqlJobService = sqlJobService;
    }

    @PostMapping
    public Result<SqlJobVO> create(@RequestBody SqlJobCreateDTO createDTO) {
        SqlJobVO sqlJobVO = sqlJobService.create(createDTO);
        return Result.success(sqlJobVO);
    }

    @PutMapping("/{id}")
    public Result<SqlJobVO> update(@PathVariable Long id, @RequestBody SqlJobUpdateDTO updateDTO) {
        updateDTO.setId(id);
        SqlJobVO sqlJobVO = sqlJobService.update(updateDTO);
        return Result.success(sqlJobVO);
    }

    @PatchMapping("/{id}")
    public Result<SqlJobVO> updateStatus(@PathVariable Long id, @RequestBody SqlJobStatusUpdateDTO updateDTO) {
        updateDTO.setId(id);
        SqlJobVO sqlJobVO = sqlJobService.statusUpdate(updateDTO);
        return Result.success(sqlJobVO);
    }
}
