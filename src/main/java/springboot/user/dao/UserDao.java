package springboot.user.dao;

import springboot.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws SQLException {
        // alter table users modify column id int auto_increment;

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("insert into  users values (?, ?, ?)");
        ) {


            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public User get(String id) throws SQLException {
        User user = new User();

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");

        ) {

            ps.setString(1, id);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {

                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/springbook", "springbook", "springbook1234");
    }
}
