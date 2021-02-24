package io.softera.rba.repository;

import io.softera.rba.domain.Admin;
import io.softera.rba.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AdminRepository {

    //SQL
    private static final String insert = "INSERT INTO admin (email, full_name, phone_no) VALUES (:email,:name,:phone)";
    private static final String selectEmail = "SELECT admin_id, email, full_name, phone_no FROM user WHERE email = :email";

    //Establish JDBC connection
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //Create row mapper
    private final RowMapper<Admin> rowMapper = (rs, rowNum) -> {
        Admin a = new Admin();
        a.setAdminId(rs.getInt("admin_id"));
        a.setEmail(rs.getString("email"));
        a.setFullName(rs.getString("full_name"));
        a.setPhoneNo(rs.getString("phone_no"));
        return a;
    };

        public int save(Admin a) {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("email", a.getEmail())
                    .addValue("name", a.getFullName())
                    .addValue("phone", a.getPhoneNo());
            return namedParameterJdbcTemplate.update(insert, params);
        }

    public Admin findByEmail(String email) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        return namedParameterJdbcTemplate.queryForObject(selectEmail, params, rowMapper);
    }

}
