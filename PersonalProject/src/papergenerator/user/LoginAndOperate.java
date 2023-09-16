package papergenerator.user;

import java.util.Scanner;
import java.util.TreeMap;

public class LoginAndOperate {
  private TreeMap<String, TeacherUser> userSites = new TreeMap<>();

  //初始化
  public LoginAndOperate() {
    userSites.put("张三1", new TeacherUser("张三1", "123", "小学"));
    userSites.put("张三2", new TeacherUser("张三2", "123", "小学"));
    userSites.put("张三3", new TeacherUser("张三3", "123", "小学"));
    userSites.put("李四1", new TeacherUser("李四1", "123", "初中"));
    userSites.put("李四2", new TeacherUser("李四2", "123", "初中"));
    userSites.put("李四3", new TeacherUser("李四3", "123", "初中"));
    userSites.put("王五1", new TeacherUser("王五1", "123", "高中"));
    userSites.put("王五2", new TeacherUser("王五2", "123", "高中"));
    userSites.put("王五3", new TeacherUser("王五3", "123", "高中"));
  }

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

  //用户登录
  public TeacherUser accountLogin(String username, String password) {
    TeacherUser temUser = userSites.get(username);
    if (temUser == null) {
      System.out.println("请输入正确的用户名、密码");
      return null;
    } else if (temUser.getUserPassword().equals(password)) {
      System.out.println("当前选择为" + temUser.getAccountType() + "出题");
      return temUser;
    } else {
      System.out.println("请输入正确的用户名、密码");
      return null;
    }
  }

  //登录成功后，用户输入选项操作
  public boolean afterLoginOperation(String username, String password) {
    TeacherUser temUser = accountLogin(username, password);  //登录
    if (temUser == null) {
      return false;
    } else  {
      while (true) {
        System.out.println("准备生成"
            + temUser.getAccountType() + "数学题目，请输入生成题目数量（输入-1将退出当前用户，重新登录）：");
        Scanner sc = new Scanner(System.in);
        String temStr = sc.nextLine();  //登录后选项：1.输入中文，切换 2.输入数字，出题
        //切换
        if (temStr.contains("切换为")) {
          String sub = temStr.substring(0, 3);
          if (sub.equals("切换为")) {
            this.changeUserAccountType(username, temStr.substring(3));
          }
        } else {
          int n = Integer.parseInt(temStr);
          if (n == -1) {
            break;
          } else if (n >= 10 && n <= 100) {
            temUser.generatePaper(n);
            System.out.println("出题完成，题目数量：" + n);
          } else {
            System.out.println("输入无效，请输入10-100之间的自然数，或输入-1退出登录，或输入切换为XX");
          }
        }
      }
    }
    return false;
  }
}
