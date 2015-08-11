/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author ������
 */
public class CaptionImplemented implements CaptionInterface {

    private JdbcTemplate jdbcTemplate;

    public CaptionImplemented(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Caption get(int contactId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caption> list() {
        String sql = "SELECT * FROM caption";
        List<Caption> listCaption = jdbcTemplate.query(sql, new RowMapper<Caption>() {
            @Override
            public Caption mapRow(ResultSet rs, int rowNum) throws SQLException {
                Caption caption = new Caption();
                caption.setId(rs.getInt("id"));
                caption.setName(rs.getString("name"));
                return caption;
            }
        });
        return listCaption;
    }

    @Override
    public void updateArgument(int id, double argument) {
        String sql = "UPDATE caption "
                + "SET argument=" + argument + " "
                + "WHERE id=" + id + "; ";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public int insertCaption(final String name) {
        final String sql = "INSERT INTO caption (caption) VALUES (?);";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps
                        = cnctn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }
        }, holder);
        return holder.getKey().intValue();
    }

}
