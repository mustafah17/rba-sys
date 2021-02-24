package io.softera.rba.repository;

import io.softera.rba.domain.Admin;
import io.softera.rba.domain.Restaurant;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RestaurantRepository {

    //SQL
    private static final String insert = "INSERT INTO restaurant (name, type_of_food, desc, admin_id) VALUES (:name,:typeOfFood,:desc, :admin_id)";

    //Establish JDBC connection
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int save(Restaurant r) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", r.getName())
                .addValue("typeOfFood", r.getTypeOfFood())
                .addValue("desc", r.getDescription())
                .addValue("admin_id", r.getAdminID());
        return namedParameterJdbcTemplate.update(insert, params);
    }
}
