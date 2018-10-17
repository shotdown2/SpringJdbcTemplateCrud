package com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.jdbc.models.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<User> listAll() {
		String sql = "SELECT id, nome, sobrenome FROM users";
		List<User> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModels(null), new UserMapper());

		return list;
	}

	private SqlParameterSource getSqlParameterByModels(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (user != null) {
			paramSource.addValue("id", user.getId());
			paramSource.addValue("nome", user.getNome());
			paramSource.addValue("sobrenome", user.getSobrenome());
		}

		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setNome(rs.getString("nome"));
			user.setSobrenome(rs.getString("sobrenome"));

			return user;
		}
	}

	public void insert(User user) {
		String sql = "INSERT INTO users(nome, sobrenome) VALUES (:nome, :sobrenome)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModels(user));
	}

	public void update(User user) {
		String sql = "UPDATE users SET nome = :nome, sobrenome = :sobrenome";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModels(user));

	}

	public void delete(int id) {
		String sql = "DELETE FROM users WHERE id = :id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModels(new User()));

	}

	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModels(new User()), new UserMapper());

	}

}
