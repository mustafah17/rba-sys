package io.softera.rba.repository;

import io.softera.rba.domain.Admin;
import io.softera.rba.domain.Booking;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class BookingRepository {

    //SQL
    private static final String insert = "INSERT INTO booking (no_of_people, user_id, table_id) VALUES (:noOfGuests,:userId,:tableId)";

    //Establish JDBC connection
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int save(Booking b) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("noOfGuests", b.getNumberOfGuests())
                .addValue("userId", b.getUserId())
                .addValue("tableId", b.getTableId());
        return namedParameterJdbcTemplate.update(insert, params);
    }
}
