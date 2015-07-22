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
public class ThermImplemented implements ThermInterface {

    private JdbcTemplate jdbcTemplate;

    public ThermImplemented(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Therm> getStackByCaptureId(int id) {
        String sql = "SELECT therm_set.id,therm_set.name "
                + "FROM intermediate "
                + "INNER JOIN caption "
                + "ON intermediate.foreign_to_caption=caption.Id "
                + "INNER JOIN criteria "
                + "ON intermediate.foreign_to_criteria=criteria.Id "
                + "INNER JOIN therm_set "
                + "ON criteria.foreign_to_therm=therm_set.id "
                + "Where intermediate.foreign_to_caption=" + id + ";";
        List<Therm> listTherm = jdbcTemplate.query(sql, new RowMapper<Therm>() {
            @Override
            public Therm mapRow(ResultSet rs, int i) throws SQLException {
                Therm therm = new Therm();
                therm.setId(rs.getInt("id"));
                therm.setName(rs.getString("name"));
                return therm;
            }
        });
        return listTherm;
    }

    @Override
    public List<Therm> getList() {
        String sql = "SELECT therm_set.id,therm_set.name "
                + "FROM therm_set ;";
        List<Therm> listTherm = jdbcTemplate.query(sql, new RowMapper<Therm>() {
            @Override
            public Therm mapRow(ResultSet rs, int i) throws SQLException {
                Therm therm = new Therm();
                therm.setId(rs.getInt("id"));
                therm.setName(rs.getString("name"));
                return therm;
            }
        });
        return listTherm;
    }

    @Override
    public void insertTherm(String name) {
        String sql = "INSERT INTO therm_set (name) VALUES ('" + name + "');";
        jdbcTemplate.update(sql);
    }

    @Override
    public void delteTherm(int id) {
        String sql = "DELETE FROM therm_set WHERE id='" + id + "';";
        jdbcTemplate.update(sql);
    }

}
