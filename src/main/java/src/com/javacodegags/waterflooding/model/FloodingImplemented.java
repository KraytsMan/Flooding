/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

import java.sql.*;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author ������
 */
public class FloodingImplemented implements FloodingInterface {

    private JdbcTemplate jdbcTemplate;

    public FloodingImplemented(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Flooding get(int criteriaId) {
        String sql = "SELECT flooding.id, flooding.name, flooding.image, flooding.description "
                + "FROM intermediatetoflooding "
                + "INNER JOIN criteria "
                + "ON intermediatetoflooding.foreign_to_criteria=criteria.Id "
                + "INNER JOIN flooding "
                + "ON intermediatetoflooding.foreign_to_flooding=flooding.id "
                + "Where criteria.id=" + criteriaId + ";";
        Flooding flooding = jdbcTemplate.query(sql, new ResultSetExtractor<Flooding>() {

            @Override
            public Flooding extractData(ResultSet rs) throws SQLException, DataAccessException {
                Flooding flooding = new Flooding();
                if (rs.next()) {
                    flooding.setId(rs.getInt("id"));
                    flooding.setName(rs.getString("name"));
                    flooding.setImage(rs.getString("image"));
                    flooding.setDescription(rs.getString("description"));
                }
                return flooding;
            }
        });
        return flooding;
    }

    @Override
    public List<Flooding> getList() {
        String sql = "SELECT * FROM flooding ;";
        List<Flooding> flooding = jdbcTemplate.query(sql, new RowMapper<Flooding>() {
            @Override
            public Flooding mapRow(ResultSet rs, int i) throws SQLException {
                Flooding flooding = new Flooding();

                flooding.setId(rs.getInt("id"));
                flooding.setName(rs.getString("name"));
                flooding.setImage(rs.getString("image"));
                flooding.setDescription(rs.getString("description"));
                return flooding;
            }
        });
        return flooding;
    }

    @Override
    public int insert(final String name) {
        final String sql = "INSERT INTO flooding (name) VALUES (?);";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps
                    = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }
        }, holder);
        return holder.getKey().intValue();
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE flooding "
            + "SET name= '" + name + "' "
            + "WHERE id=" + id + "; ";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM flooding WHERE id='" + id + "';";
        jdbcTemplate.update(sql);
    }

}
