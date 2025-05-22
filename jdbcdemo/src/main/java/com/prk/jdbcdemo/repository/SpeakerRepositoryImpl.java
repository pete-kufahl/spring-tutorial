package com.prk.jdbcdemo.repository;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.repository.util.SpeakerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = jdbcTemplate.query("select * from speaker", new SpeakerRowMapper());
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
        jdbcTemplate.update("INSERT INTO speaker (name) values (?)", speaker.getName());

        // non-SQL version
//        var insert = new SimpleJdbcInsert(jdbcTemplate);
//        insert.setTableName("speaker");
//        List<String> columns = new ArrayList<>();
//        columns.add("name");
//        Map<String, Object> data = new HashMap<>();
//        data.put("name", speaker.getName());
//        insert.setGeneratedKeyName("id");
//        Number key = insert.executeAndReturnKey(data);
//        System.out.println(key);
        return null;
    }
}
