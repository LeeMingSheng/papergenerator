package papergenerator.user;

import papergenerator.paper.FileGenerator;
import papergenerator.paper.JuniorPaperGenerator;
import papergenerator.paper.PrimaryPaperGenerator;
import papergenerator.paper.SeniorPaperGenerator;

import java.io.File;

public class TeacherUser {
  private String userName;
  private String userPassword;
  private String accountType;
  private PrimaryPaperGenerator primaryPaperGenerator = new PrimaryPaperGenerator();
  private JuniorPaperGenerator juniorPaperGenerator = new JuniorPaperGenerator();
  private SeniorPaperGenerator seniorPaperGenerator = new SeniorPaperGenerator();

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

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

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