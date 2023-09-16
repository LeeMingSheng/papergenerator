package papergenerator.paper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

  // 写入文件
  public void writeIn(File file, Integer num, String title) {
    try {
      FileWriter fileWriter = new FileWriter(file, true); // 传入文件，类型为续写
      String serialNumber = num.toString() + ".";
      fileWriter.append(serialNumber).append(" ").append(title);
      fileWriter.append("\r\n"); // 换行
      fileWriter.append("\r\n");
      fileWriter.flush(); // 刷新文件
      fileWriter.close(); // 关闭
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
