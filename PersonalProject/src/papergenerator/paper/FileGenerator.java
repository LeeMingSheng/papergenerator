package papergenerator.paper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import papergenerator.user.TeacherUser;


/**
 * Pass in the teacher object and create the corresponding file.
 */
public class FileGenerator {
  /**
   * Pass in the teacher object and create the corresponding file.
   *
   * @param temUser Teacher object that needs to create files
   * @return Return the newly created file
   */
  public File fileGenerator(TeacherUser temUser) {
    String freshPath = "src\\Paper\\" + temUser.getUserName();
    File file = new File(freshPath);
    Date currentdate = new Date();
    // 设置txt文件名称
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    String fileTitle = simpleDateFormat.format(currentdate);
    String absolutePath = file.getAbsolutePath();
    if (!file.exists()) {
      boolean mkFlag = file.mkdirs();  // 如果没有对应文件夹，则生成
      if (mkFlag) {
        System.out.println(temUser.getUserName() + "教师文件夹已创建,文件路径：" + absolutePath);
      } else {
        System.out.println("文件夹创建失败");
      }
    }
    File freshPaper = new File(freshPath, fileTitle + ".txt");
    try {
      freshPaper.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("文件创建过程中出现异常: " + e.getMessage());
    }
    return freshPaper;
  }
}
