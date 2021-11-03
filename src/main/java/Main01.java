import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class Main01 {

    public static void main(String[] args) {
//       User drugi = new User();
//
//     drugi.setEmail("innyemail7@gmail.com");
//      drugi.setUserName("drugiuser");
//     drugi.setPassword("drugipierwszego");
//
//     System.out.println(drugi.getId());
//
//
//      UserDao.create(drugi);
//      User drugi = UserDao.read(1);
//        UserDao.change(drugi);
//        UserDao.update(drugi);
//        UserDao.delete(2);
//        UserDao.delete(9);
//        UserDao.delete(10);
//        UserDao.delete(11);
//        UserDao.delete(7);
//               User drugi = new User();

//     drugi.setEmail("ikolejnymaikl@gmail.com");
//      drugi.setUserName("czwartyuser");
//     drugi.setPassword("drasdsadpierwszego");
//        UserDao.create(drugi);
//    User marek = UserDao.read(9);
//        System.out.println(marek.getUserName());
//     UserDao.findAll();
//
//        User majster = UserDao.read(4);
//        System.out.println(majster.getUserName());
        User dozmiany = UserDao.read(1);
        UserDao.change(dozmiany);
        UserDao.update(dozmiany);


    }
}
