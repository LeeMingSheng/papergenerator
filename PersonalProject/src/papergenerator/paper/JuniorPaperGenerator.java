package papergenerator.paper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Classes for generating junior high school questions.
 */
public class JuniorPaperGenerator extends PaperGenerator {

  //初中符号
  private TreeMap<Integer, String> symbolForJunior = new TreeMap<>();

  /**
   * Get the collection of primary school symbols.
   */
  public TreeMap<Integer, String> getPrimarySymbol() {
    return symbolForPrimary;
  }

  /**
   * Get the collection of junior high school symbols.
   */
  public TreeMap<Integer, String> getJuniorSymbol() {
    return symbolForJunior;
  }

  /**
   * Constructor for juniorPaperGenerator.
   */
  public JuniorPaperGenerator() {
    symbolForPrimary.put(0, "+");
    symbolForPrimary.put(1, "-");
    symbolForPrimary.put(2, "×");
    symbolForPrimary.put(3, "÷");
    symbolForJunior.put(0, "^2");
    symbolForJunior.put(1, "√");
  }

  /**
   * Call the method to generate test papers based on the number of questions.
   *
   * @param n         The number of questions generated
   * @param freshFile File written
   */
  @Override
  public void generatePaper(int n, File freshFile) {
    HashSet<String> generatedProblems = new HashSet<>();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(freshFile, true))) {
      for (int i = 0; i < n; i++) {
        StringBuilder stringBuilder = new StringBuilder();
        problemGenerator.generateJuniorProblem(stringBuilder);
        String problem = stringBuilder.toString();

        // 查重
        if (generatedProblems.contains(problem)) {
          i--;
          continue;
        }

        // 将题目添加到查重集合中
        generatedProblems.add(problem);

        String serialNumber = (i + 1) + ".";
        writer.write(serialNumber + " " + problem);
        writer.newLine(); // 换行
        writer.newLine(); // 空行
      }
      writer.flush(); // 刷新文件
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("向文件写入数据失败");
    }
  }

  /**
   * Check if there are duplicate questions.
   *
   * @param file    File to store questions
   * @param problem Pass in the question to be compared
   * @return boolean The return value indicates whether the question is repeated
   */
  // 查重
  @Override
  public boolean repetitionCheck(File file, String problem) {
    String upPath = file.getParent();  // 获取txt文件上一级目录的路径
    Path upFolderPath = Paths.get(upPath);
    final boolean[] repetition = {false}; // 是否重复
    try {
      Files.walk(upFolderPath)
          .filter(p -> p.toFile().isFile() && p.toString().endsWith(".txt"))
          .anyMatch(p -> {
            try (BufferedReader bufferedReader = Files.newBufferedReader(p)) {
              String temTitle;
              while ((temTitle = bufferedReader.readLine()) != null) {
                String[] titleSymbol = temTitle.split(" ");
                if (titleSymbol.length >= 2 && titleSymbol[1].equals(problem)) {
                  System.out.println("重复");
                  repetition[0] = true;
                  return true;
                }
              }
            } catch (IOException e) {
              System.out.println("读写时发生错误");
              e.printStackTrace();
            }
            return false;
          });
    } catch (IOException e) {
      System.out.println("遍历文件时发生错误");
      e.printStackTrace();
    }
    return repetition[0];
  }
}
