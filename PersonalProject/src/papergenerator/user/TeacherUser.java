package papergenerator.user;

import java.io.File;
import papergenerator.paper.FileGenerator;
import papergenerator.paper.JuniorPaperGenerator;
import papergenerator.paper.PrimaryPaperGenerator;
import papergenerator.paper.SeniorPaperGenerator;

public class TeacherUser {
  /**
   * userName of TeacherUser.
   */
  private String userName;
  /**
   * userPassword of TeacherUser.
   */
  private String userPassword;
  /**
   * accountType of TeacherUser.
   */
  private String accountType;
  /**
   * Class for creating primary school topics.
   */
  PrimaryPaperGenerator primaryPaperGenerator = new PrimaryPaperGenerator();
  /**
   * Class for creating junior high school topics.
   */
  JuniorPaperGenerator juniorPaperGenerator = new JuniorPaperGenerator();
  /**
   * Class for creating senior high school topics.
   */
  SeniorPaperGenerator seniorPaperGenerator = new SeniorPaperGenerator();

  /**
   * constructor for TeacherUser.
   */
  public TeacherUser(String userName, String userPassword, String accountType) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.accountType = accountType;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public String getAccountType() {
    return accountType;
  }

  /**
   * Reset user type.
   */
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  /**
   * Generate test papers based on type.
   *
   * @param n Accepted parameters, the number of questions to be generated
   */
  //生成试卷1
  public void generatePaper(int n) {
    FileGenerator fileGenerator = new FileGenerator();
    File freshFile = fileGenerator.fileGenerator(this);
    switch (this.accountType) {
      case "小学":
        primaryPaperGenerator.generatePaper(n, freshFile);
        break;
      case "初中":
        juniorPaperGenerator.generatePaper(n, freshFile);
        break;
      case "高中":
        seniorPaperGenerator.generatePaper(n, freshFile);
        break;
      default:
        break;
    }
  }
}
