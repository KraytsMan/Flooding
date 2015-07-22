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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author ������
 */
public class CriteriaImplemented implements CriteriaInterface {

    private JdbcTemplate jdbcTemplate;

    public CriteriaImplemented(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Criteria get(int criteriaId) {
        String sql = "SELECT criteria.id, criteria.criteria_value,criteria.weight_factor, criteria.formula, caption.argument,criteria.foreign_to_therm "
                + "FROM intermediate "
                + "INNER JOIN geology.caption "
                + "ON geology.intermediate.foreign_to_caption=geology.caption.Id "
                + "INNER JOIN geology.criteria "
                + "ON geology.intermediate.foreign_to_criteria=geology.criteria.Id "
                + "where criteria.Id=" + criteriaId + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Criteria>() {

            @Override
            public Criteria extractData(ResultSet rs) throws SQLException, DataAccessException {
                Criteria criteria = new Criteria();
                if (rs.next()) {
                    criteria.setId(rs.getInt("id"));
                    criteria.setValue(rs.getDouble("criteria_value"));
                    criteria.setArgument(rs.getDouble("argument"));
                    criteria.setFormula(rs.getString("formula"));
                    criteria.setWeighFactor(rs.getDouble("weight_factor"));
                    criteria.setTherms(rs.getInt("foreign_to_therm"));
                }
                return criteria;
            }
        });
    }

    @Override
    public List<Criteria> getListById(int id) {
        String sql = "SELECT criteria.id, criteria_value, formula "
                + "FROM intermediate "
                + "INNER JOIN caption "
                + "ON intermediate.foreign_to_caption=caption.Id "
                + "INNER JOIN criteria "
                + "ON intermediate.foreign_to_criteria=criteria.Id "
                + "Where intermediate.foreign_to_caption=" + id + ";";
        List<Criteria> listCriteria = jdbcTemplate.query(sql, new RowMapper<Criteria>() {
            @Override
            public Criteria mapRow(ResultSet rs, int rowNum) throws SQLException {
                Criteria criteria = new Criteria();
                criteria.setId(rs.getInt("id"));
                criteria.setValue(rs.getDouble("criteria_value"));
                criteria.setFormula(rs.getString("formula"));
                return criteria;
            }
        });
        return listCriteria;
    }

    @Override
    public List<Criteria> getAll() {
        String sql = "SELECT criteria.id, criteria.criteria_value,criteria.weight_factor, criteria.formula, caption.argument "
                + "                FROM intermediate "
                + "                INNER JOIN caption "
                + "                ON intermediate.foreign_to_caption=caption.Id "
                + "                INNER JOIN criteria "
                + "                ON intermediate.foreign_to_criteria=criteria.Id";
        List<Criteria> listCriteria = jdbcTemplate.query(sql, new RowMapper<Criteria>() {
            @Override
            public Criteria mapRow(ResultSet rs, int rowNum) throws SQLException {
                Criteria criteria = new Criteria();
                criteria.setId(rs.getInt("id"));
                criteria.setValue(rs.getDouble("criteria_value"));
                criteria.setArgument(rs.getDouble("argument"));
                criteria.setFormula(rs.getString("formula"));
                criteria.setWeighFactor(rs.getDouble("weight_factor"));
                return criteria;
            }
        });
        return listCriteria;
    }

    @Override
    public void updateFunction(int id, double value) {
        String sql = "UPDATE criteria "
                + "SET criteria_value=" + value + " "
                + "WHERE id=" + id + "; ";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public void updateCriteria(Criteria c) {
        String sql = "UPDATE criteria SET formula='" + c.getFormula() + "',"
                + " weight_factor='" + c.getWeighFactor() + "', foreign_to_therm='" + c.getTherms() + "'"
                + " WHERE Id='" + c.getId() + "';";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public int insertCriteria(int id) {
        final String sql = "INSERT INTO criteria (foreign_to_therm) VALUES ("+id+");";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps
                        = cnctn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, holder);
        return holder.getKey().intValue();
    }

    @Override
    public void insertIntermediateToCaption(int criteria, int caption) {
        String sql = "INSERT INTO intermediate (foreign_to_criteria, foreign_to_caption) VALUES (" + criteria + "," + caption + ");";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public void insertIntermediateToFlooding(int criteria, int flooding) {
        String sql = "INSERT INTO intermediatetoflooding (foreign_to_criteria, foreign_to_flooding) VALUES (" + criteria + "," + flooding + ");";
        this.jdbcTemplate.update(sql);
    }

}
