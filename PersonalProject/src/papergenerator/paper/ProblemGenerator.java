package papergenerator.paper;

import java.util.Random;

/**
 * The specific class to generate test questions.
 */
public class ProblemGenerator {
  SymbolGenerator symbolGenerator = new SymbolGenerator();

  /**
   * Methods to generate primary school test questions.
   */
  public void generatePrimaryProblem(StringBuilder stringBuilder) {

    int parenthesis = 0;  //判断括号
    int operateNum = 5;  //操作数上限
    boolean quitCheck = false;
    Random random = new Random();
    int randomNum;
    while (operateNum-- > 0) {
      randomNum = random.nextInt(2); // 判断本轮是否退出
      if (operateNum <= 3) {
        if (randomNum == 0 || operateNum == 0) {
          quitCheck = true; // 本轮退出
        }
      } else {
        quitCheck = false;
      }
      Object[] objects = symbolGenerator.appendLeftParenthesis(parenthesis, quitCheck, stringBuilder); // 随机添加左括号
      parenthesis = (Integer) objects[1]; // 括号数
      symbolGenerator.appendNum(stringBuilder); // 添加数字
      parenthesis = symbolGenerator.appendRightParenthesis(parenthesis, (Boolean) objects[0], stringBuilder); // 判定添加右括号
      if (quitCheck) {
        break; // 随机结束
      }
      symbolGenerator.appendSymbol(stringBuilder); // 随机添加数学符号
    }
    symbolGenerator.closeParenthesis(parenthesis, stringBuilder); // 补全，添加右括号
    stringBuilder.append("=");

  }

  /**
   * Methods to generate junior high school test questions.
   */
  public void generateJuniorProblem(StringBuilder stringBuilder) {
    // 判断括号
    int parenthesis = 0;
    // 操作数最多个数
    int operateNum = 5;
    boolean quitCheck = false;
    Random random = new Random();
    int randomNum = 0;
    boolean evolutionAndSquare = false;
    while (operateNum-- > 0) {
      randomNum = random.nextInt(2); // 判断本轮是否退出
      if (operateNum <= 3) {
        if (randomNum == 0 || operateNum == 0) {
          quitCheck = true; // 本轮退出
        }
      } else {
        quitCheck = false;
      }
      evolutionAndSquare = symbolGenerator.appendEvolution(stringBuilder, evolutionAndSquare); //随机添加根号
      Object[] objects = symbolGenerator.appendLeftParenthesis(parenthesis, quitCheck, stringBuilder); // 随机添加左括号
      parenthesis = (Integer) objects[1];
      symbolGenerator.appendNum(stringBuilder); //添加数字
      symbolGenerator.appendSquare(stringBuilder, evolutionAndSquare, operateNum); // 随机添加平方
      parenthesis = symbolGenerator.appendRightParenthesis(parenthesis, (Boolean) objects[0], stringBuilder); // 判断添加右括号
      if (quitCheck) {
        break; // 随机结束
      }
      symbolGenerator.appendSymbol(stringBuilder); // 随机生成符号
    }
    // 补全 )
    symbolGenerator.closeParenthesis(parenthesis, stringBuilder);
    stringBuilder.append("=");
  }

  /**
   * Methods to generate senior high school test questions.
   */
  public void generateSeniorProblem(StringBuilder stringBuilder) {
    // 判断括号
    int parenthesis = 0;
    // 操作数最多个数
    int operateNum = 5;
    Random random = new Random();
    int triFuction = random.nextInt(3) + 1;
    boolean quitCheck = false;
    int randomNum = 0;
    while (operateNum-- > 0) {
      randomNum = random.nextInt(2); // 判断本轮是否退出
      if (operateNum <= 3) {
        if (randomNum == 0 || operateNum == 0) {
          quitCheck = true; // 本轮退出
        }
      } else {
        quitCheck = false;
      }
      boolean evolutionAndSquare = false;
      evolutionAndSquare = symbolGenerator.appendEvolution(stringBuilder, evolutionAndSquare); //随机添加根号
      Object[] objects = symbolGenerator.appendLeftParenthesis(parenthesis, quitCheck, stringBuilder); // 随机添加左括号
      parenthesis = (Integer) objects[1];
      triFuction = symbolGenerator.appendTriFunction(triFuction, stringBuilder); //随机添加三角函数
      symbolGenerator.appendNum(stringBuilder); //添加数字
      symbolGenerator.appendSquare(stringBuilder, true, 2); //随机添加平方
      parenthesis = symbolGenerator.appendRightParenthesis(parenthesis, (Boolean) objects[0], stringBuilder); // 判断添加右括号
      if (quitCheck) {
        break; //随机结束
      }
      symbolGenerator.appendSymbol(stringBuilder); // 随机生成符号
    }
    // 补全 )
    symbolGenerator.closeParenthesis(parenthesis, stringBuilder);
    stringBuilder.append("=");
  }
}
