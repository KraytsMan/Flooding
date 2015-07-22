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
public class ParametersImplemented implements ParameterInterface {

    private JdbcTemplate jdbcTemplate;

    public ParametersImplemented(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Parameters get(int parameterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Parameters> getListById(int parameterId) {
        String sql = "SELECT parameters.id, parameters.name, parameters.value "
                + "FROM parameters "
                + "INNER JOIN criteria "
                + "ON parameters.foreign_to_criteria=criteria.Id "
                + "Where parameters.foreign_to_criteria=" + parameterId + ";";
        List<Parameters> listParameters = jdbcTemplate.query(sql, new RowMapper<Parameters>() {
            @Override
            public Parameters mapRow(ResultSet rs, int rowNum) throws SQLException {
                Parameters parameters = new Parameters();
                parameters.setId(rs.getInt("id"));
                parameters.setName(rs.getString("name"));
                parameters.setValue(rs.getFloat("value"));
                return parameters;
            }
        });
        return listParameters;
    }

    @Override
    public void deleteParams(int parameterId) {
        String sql = "Delete "
                + "from parameters "
                + "where foreign_to_criteria=" + parameterId + ";";
        jdbcTemplate.update(sql);
    }

    @Override
    public void insertParams(Parameters parameters) {
        String sql = "INSERT INTO parameters (name,value,foreign_to_criteria) VALUES ('"
                + parameters.getName() + "','"
                + parameters.getValue() + "', '"
                + parameters.getForeignId() + "');";
        jdbcTemplate.update(sql);
    }

}
