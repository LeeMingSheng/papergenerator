package papergenerator.paper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeMap;

public class JuniorPaperGenerator extends PaperGenerator {
  //初中符号
  private TreeMap<Integer, String> symbolForJunior = new TreeMap<>();

  public TreeMap<Integer, String> getPrimarySymbol() {
    return symbolForPrimary;
  }

  public TreeMap<Integer, String> getJuniorSymbol() {
    return symbolForJunior;
  }

  public JuniorPaperGenerator() {
    symbolForPrimary.put(0, "+");
    symbolForPrimary.put(1, "-");
    symbolForPrimary.put(2, "×");
    symbolForPrimary.put(3, "÷");
    symbolForJunior.put(0, "^2");
    symbolForJunior.put(1, "√");
  }

  public void generatePaper(int n, File freshFile) {
    for (int i = 0; i < n; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      problemGenerator.generateJuniorProblem(stringBuilder);
      String problem = stringBuilder.toString();
      // 查重
      if (this.repetitionCheck(freshFile, problem)) {
        i--;
        continue;
      }
      Writer writer = new Writer();
      writer.writeIn(freshFile, i + 1, problem); //写入
    }
  }

  // 查重
  public boolean repetitionCheck(File file, String problem) {
    String upPath = file.getParent();  // 获取txt文件上一级目录的路径
    File upDir = new File(upPath);
    File[] upFiles = upDir.listFiles(); // 遍历上一级目录的所有文件
    boolean repetition = false; // 是否重复
    if (upFiles == null) {
      return repetition;
    }
    //逐文件进行对比
    for (int i = 0; i < upFiles.length; i++) {
      try {
        FileReader fileReader = new FileReader(upFiles[i]); // 第i个文件
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
          String temTitle = bufferedReader.readLine(); // 读一行数据
          if (temTitle == null) {
            break; // 跳过空行
          }
          String[] titleElements = temTitle.split(" "); // 标号和题目之间，以空格作为分隔
          if (titleElements.length >= 2 && titleElements[1].equals(problem)) {
            System.out.println("重复");
            repetition = true; //重复
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return repetition;
  }
}
