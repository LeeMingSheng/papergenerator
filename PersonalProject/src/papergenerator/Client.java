package papergenerator;

import papergenerator.user.LoginAndOperate;

import java.util.Scanner;

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
