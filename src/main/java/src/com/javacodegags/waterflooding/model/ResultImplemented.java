/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ������
 */
public class ResultImplemented implements ResultInterface {

    private JdbcTemplate jdbcTemplate;

    public ResultImplemented(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Result get(int criteriaId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Result> getAll() {
        String sql = "SELECT * FROM results";
        List<Result> listCaption = jdbcTemplate.query(sql, new RowMapper<Result>() {
            @Override
            public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
                Result result = new Result();
                result.setId(rs.getInt("id"));
                result.setMinimum(rs.getDouble("minimum"));
                result.setAverage(rs.getDouble("average"));
                return result;
            }
        });
        return listCaption;
    }

    @Override
    public void updateResult(int id) {
        String sql = "Update results "
                + "Set results.minimum=(SELECT min(criteria.criteria_value) "
                + "FROM intermediatetoflooding "
                + "INNER JOIN criteria "
                + "ON intermediatetoflooding.foreign_to_criteria=criteria.Id "
                + "INNER JOIN flooding "
                + "ON intermediatetoflooding.foreign_to_flooding=flooding.id "
                + "Where flooding.id=" + id + "), "
                + "results.average=(SELECT avg(criteria.criteria_value) "
                + "FROM intermediatetoflooding "
                + "INNER JOIN criteria "
                + "ON intermediatetoflooding.foreign_to_criteria=criteria.Id "
                + "INNER JOIN flooding "
                + "ON intermediatetoflooding.foreign_to_flooding=flooding.id "
                + "Where flooding.id=" + id + ")"
                + "Where results.id=" + id + ";";
        this.jdbcTemplate.update(sql);
    }

}
