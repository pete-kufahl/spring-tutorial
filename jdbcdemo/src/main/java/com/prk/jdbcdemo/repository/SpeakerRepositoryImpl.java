package com.prk.jdbcdemo.repository;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.repository.util.SpeakerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                var ps = con.prepareStatement("INSERT INTO speaker (name) values (?)",
                        new String[] { "id" });
                ps.setString(1, speaker.getName());
                return ps;
            }
        }, keyHolder);
        var id = keyHolder.getKey();
        return getSpeaker(id.intValue());
    }

    public Speaker easierCreate(Speaker speaker) {
        var insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("speaker");
        List<String> columns = new ArrayList<>();
        columns.add("name");
        insert.setColumnNames(columns);
        Map<String, Object> data = new HashMap<>();
        data.put("name", speaker.getName());
        insert.setGeneratedKeyName("id");
        var id = insert.executeAndReturnKey(data);
        return getSpeaker(id.intValue());
    }

    private Speaker getSpeaker(int id) {
        return jdbcTemplate.queryForObject("select * from speaker where id = ?",
                new SpeakerRowMapper(), id);
    }
}
