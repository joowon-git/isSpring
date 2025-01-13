package springboot.main;

import springboot.user.dao.UserDao;
import springboot.user.domain.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user1 = new User();
        User user2 = new User();

        user1.setId("jooyong-id");
        user1.setName("jooyong");
        user1.setPassword("jooyong1234");
        user2.setId("joowon-id");
        user2.setName("joowon");
        user2.setPassword("joowon1234");

        try {
            userDao.add(user1);
            userDao.add(user2);

            System.out.println("등록 성공! " + user1.getId());
            System.out.println("등록 성공! " + user2.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(userDao.get(user1.getId()).getName());
            System.out.println("조회 성공! " + user1.getId());
            System.out.println(userDao.get(user2.getId()).getName());
            System.out.println("조회 성공! " + user2.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
