package io.softera.rba.repository;

import io.softera.rba.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepository {

    private static final String insert = "INSERT INTO user (email, full_name, phone_no) VALUES (:email,:name,:phone)";
    private static final String selectEmail = "SELECT user_id, email, full_name, phone_no FROM user WHERE email = :email";

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setEmail(rs.getString("email"));
        u.setFullName(rs.getString("full_name"));
        u.setPhoneNo(rs.getString("phone_no"));
        return u;
    };

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //Insert user into db, returns number of rows updated
    public int save(User user) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("name", user.getFullName())
                .addValue("phone", user.getPhoneNo());
        return namedParameterJdbcTemplate.update(insert, params);
    }

    //Query for criteria
    public User findByEmail(String email) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        return namedParameterJdbcTemplate.queryForObject(selectEmail, params, rowMapper);
    }
}
