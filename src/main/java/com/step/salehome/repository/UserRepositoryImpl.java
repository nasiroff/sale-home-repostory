package com.step.salehome.repository;

import com.step.salehome.constants.MessageConstants;
import com.step.salehome.constants.UserConstants;
import com.step.salehome.exceptions.DuplicateEmailException;
import com.step.salehome.exceptions.InvalidTokenException;
import com.step.salehome.model.Role;
import com.step.salehome.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final String INSERT_NEW_USER_SQL = "insert into user(email, password, first_name, last_name, token, id_role, status) values (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_USER_BY_EMAIL = "select * from user u inner join role r on u.id_role = r.id_role left join favorite_post fp on u.id_user = fp.id_user where u.email = ? and u.status = 1";
    private final String UPDATE_USER_STATUS_BY_TOKEN = "update user set token = ?, status = ? where token = ?";
    private final String GET_EMAIL_COUNT_SQL = "select count(*) as say from user where email=?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void registerUser(User user) throws DuplicateEmailException {/*      if (!isEmailValid(user.getEmail())) { throw new DuplicateEmailException(MessageConstants.ERROR_DUBLICATE_EMAIL); }*/
        try {
            jdbcTemplate.update(INSERT_NEW_USER_SQL, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getToken(), user.getRole().getIdRole(), user.getStatus());
        } catch (DuplicateKeyException sql) {
            throw new DuplicateEmailException(MessageConstants.ERROR_DUBLICATE_EMAIL);
        }
    }

    @Override
    public User loginUser(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, new Object[]{email}, new RowMapper<User>() {
                @Nullable
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setToken(rs.getString("token"));
                    user.setStatus(rs.getInt("status"));
                    Role role = new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    role.setRoleType(rs.getString("role_type"));
                    user.setRole(role);
                    do user.addIdFavoritePost(rs.getInt("id_post")); while (rs.next());
                    return user;
                }
            });
        } catch (EmptyResultDataAccessException er) {
            return null;
        }
        return user;
    }

    @Override
    public void updateUserStatusByToken(String token, String newToken) throws InvalidTokenException {
        int status = UserConstants.USER_STATUS_ACTIVE;
        int okay = jdbcTemplate.update(UPDATE_USER_STATUS_BY_TOKEN, newToken, status, token);
        if (okay == 0) throw new InvalidTokenException(MessageConstants.ERROR_MESSAGE_INVALID_TOKEN);
    }

    private boolean isEmailValid(String email) {
        boolean result = false;
        int count = jdbcTemplate.queryForObject(GET_EMAIL_COUNT_SQL, new Object[]{email}, Integer.class);
        if (count == 0) result = true;
        return result;
    }
}
