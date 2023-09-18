package papergenerator.user;

import java.util.Scanner;
import java.util.TreeMap;

public class LoginAndOperate {
  /**
   * Store user accounts and passwords.
   */
  private TreeMap<String, TeacherUser> userSites = new TreeMap<>();

  /**
   * Constructor for LoginAndOperate.
   */
  //初始化
  public LoginAndOperate() {
    primarySetAccount();
  }

  /**
   * Initialize account password data.
   */
  public void primarySetAccount() {
    String[] names = {"张三1", "张三2", "张三3", "李四1", "李四2", "李四3", "王五1", "王五2", "王五3"};
    String[] schoolTypes = {"小学", "小学", "小学", "初中", "初中", "初中", "高中", "高中", "高中"};
    int i = 0;
    int j = 0;
    for (; j < schoolTypes.length && i < names.length; i++, j++) {
      userSites.put(names[i], new TeacherUser(names[i], "123", schoolTypes[j]));
    }
  }

  /**
   * Change user type.
   *
   * @param username Username that needs to be changed
   * @param type     changed type
   */
  //更改用户类型
  public void changeUserAccountType(String username, String type) {
    Scanner sc = new Scanner(System.in);
    TeacherUser temUser = userSites.get(username);
    while (true) {
      if (type.equals("小学") || type.equals("初中") || type.equals("高中")) {
        temUser.setAccountType(type);
        return;
      } else {
        System.out.println("输入无效，请输入小学、初中或者高中任意一个");
        type = sc.next();
      }
    }
  }

  /**
   * User login method.
   *
   * @param username Entered username
   * @param password Password entered
   * @return Return to TeacherUser after successful login
   */
  //用户登录
  public TeacherUser accountLogin(String username, String password) {
    TeacherUser temUser = userSites.get(username);
    String errorMessage = "请输入正确的用户名、密码";
    if (temUser == null || !temUser.getUserPassword().equals(password)) {
      System.out.println(errorMessage);
      return null;
    }
    System.out.println("登录成功！当前账户类型为" + temUser.getAccountType());
    return temUser;
  }



  /**
   * After successful login, the user enters the option operation.
   *
   * @param username Entered username
   * @param password Password entered
   * @return boolean
   */
  //登录成功后，用户输入选项操作
  public boolean afterLoginOperation(String username, String password) {
    TeacherUser temUser = accountLogin(username, password);  //登录
    if (temUser == null) {
      return false;
    } else {
      while (true) {
        System.out.println("准备生成"
            + temUser.getAccountType() + "数学题目，请输入生成题目数量（输入-1将退出当前用户，重新登录）：");
        Scanner sc = new Scanner(System.in);
        String temStr = sc.nextLine();  //登录后选项：1.输入中文，切换 2.输入数字，出题
        //切换
        try {
          if (temStr.contains("切换为")) {
            String sub = temStr.substring(0, 3);
            if (sub.equals("切换为")) {
              if (temStr.length() != 5) {
                System.out.println("输入格式无效，请输入‘切换为xx’");
              } else {
                this.changeUserAccountType(username, temStr.substring(3, 5));
              }
            }
          } else {
            int n = Integer.parseInt(temStr);
            if (n == -1) {
              primarySetAccount();
              break;
            } else if (n >= 10 && n <= 30) {
              temUser.generatePaper(n);
              System.out.println("出题完成，题目数量：" + n);
            } else {
              System.out.println("输入无效，请输入10-30之间的自然数，或输入-1退出登录，或输入切换为XX");
            }
          }
        } catch (NumberFormatException e) {
          System.out.println("无效输入，请输入整数");
        }
      }
    }
    return false;
  }
}
