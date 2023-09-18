package papergenerator;

import java.util.Scanner;
import papergenerator.user.LoginAndOperate;

/**
 * Main function, used for user interaction.
 *
 * @author MingShengLee
 * @date 2023/09/17
 */
public class Client {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    LoginAndOperate op = new LoginAndOperate();
    while (true) {
      System.out.println("请输入用户名和密码");
      String s1 = sc.next();
      String s2 = sc.nextLine();
      if (op.afterLoginOperation(s1.trim(), s2.trim())) {
        break;
      }
    }
  }
}
