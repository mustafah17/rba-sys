package io.softera.rba.repository;

import io.softera.rba.domain.Table;
import io.softera.rba.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TableRepository {

    private static final String insert = "INSERT INTO dining_table (table_no, restaurant_id, size) VALUES (:tableNo, :restaurantId, :tableCapacity)";
    private static String selectSize = "SELECT table_id, table_no, restaurant_id, size FROM dining_table WHERE size=:tableCapacity";

    //Create Row mapper
    private final RowMapper<Table> rowMapper = (rs, rowNum) -> {
        Table t = new Table();
        t.setTableId(rs.getInt("table_id"));
        t.setTableNo(rs.getInt("table_no"));
        t.setRestaurantId(rs.getInt("restaurant_id"));
        t.setTableSize(rs.getInt("size"));
        return t;
    };

    //Establish connection to h2 in memory database
    @Resource
    private NamedParameterJdbcTemplate jdbcTemplate;

    //Insert user into db, return number of rows updated
    public int save(Table t) {
        SqlParameterSource params = new MapSqlParameterSource()     //parameters to insert
                .addValue("tableNo", t.getTableNo())
                .addValue("restaurantId", t.getRestaurantId())
                .addValue("tableCapacity", t.getTableSize());
        return jdbcTemplate.update(insert, params);
    }

    //Return tables given the table size/capacity
    public Table findBySize(int capacity) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tableCapacity", capacity);
        return jdbcTemplate.queryForObject(selectSize, params, rowMapper);
    }



}
