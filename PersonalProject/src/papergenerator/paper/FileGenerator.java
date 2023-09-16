package papergenerator.paper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import papergenerator.user.TeacherUser;


public class FileGenerator {
  public File fileGenerator(TeacherUser temUser) {
    String freshPath = "src\\Paper\\" + temUser.getUserName();
    File file = new File(freshPath);
    Date date = new Date();
    // 设置txt文件名称
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    String title = simpleDateFormat.format(date) + ".txt";
    if (!file.exists()) {
      file.mkdirs();  // 如果没有对应文件夹，则生成
    }
    File freshPaper = new File(freshPath, title);
    try {
      freshPaper.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return freshPaper;
  }
}
