package com.test.crud.dao.impl;

import com.test.crud.dao.UserDao;
import com.test.crud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    @PostConstruct
    private void initialize() {

        setDataSource(dataSource);
    }


    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM testdb.public.user";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        ArrayList<User> resultList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            User user = new User();
            user.setId((Long) row.get("id"));
            user.setName((String) row.get("name"));
            user.setSurname((String) row.get("surname"));
            user.setAge((Integer) row.get("age"));
            resultList.add(user);
        }
        return resultList;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO testdb.public.user (name, surname, age) VALUES (?, ?, ?)";
        getJdbcTemplate().update(sql, user.getSurname(), user.getName(), user.getAge());
        return user;
    }

    @Override
    public User findByIdUser(Long id) {
        String sql = "SELECT * FROM testdb.public.user WHERE id=?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                return user;
            }
        });
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE testdb.public.user SET name=?, surname=?, age=? WHERE id=?";
        getJdbcTemplate().update(sql, user.getName(), user.getSurname(), user.getAge(), user.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM testdb.public.user WHERE id=?";
        getJdbcTemplate().update(sql, id);
    }
}
