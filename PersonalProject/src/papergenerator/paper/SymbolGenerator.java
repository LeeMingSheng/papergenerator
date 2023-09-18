package papergenerator.paper;

import java.util.Random;

/**
 * Generate each element of the question.
 */
public class SymbolGenerator {
  /**
   * constructor of SymbolGenerator.
   */
  public SymbolGenerator() {
  }

  /**
   * Randomly generate numbers from 1-100.
   *
   * @param stringBuilder changed buffer
   */
  // 随机生成1-100的数字
  public void appendNum(StringBuilder stringBuilder) {
    Random random = new Random();
    int randomNum = random.nextInt(100) + 1;
    stringBuilder.append(randomNum);
  }

  /**
   * Randomly add operators.
   *
   * @param stringBuilder changed buffer
   */
  // 随机添加运算符
  public void appendSymbol(StringBuilder stringBuilder) {
    PrimaryPaperGenerator primary = new PrimaryPaperGenerator();
    Random random = new Random();
    int num = random.nextInt(4);
    String symbol = primary.getPrimarySymbol().get(num);
    stringBuilder.append(symbol);
  }

  /**
   * Randomly add left bracket.
   *
   * @param parenthesis   Number of parenthesis added
   * @param quitCheck     Whether to exit this round of addition
   * @param stringBuilder changed buffer
   * @return Return the result of adding parentheses
   */
  // 随机添加左括号(
  public Object[] appendLeftParenthesis(Integer parenthesis,
                                        boolean quitCheck, StringBuilder stringBuilder) {
    Random random = new Random();
    //返回值有多个
    Object[] object = new Object[2];
    int parenthesisFlag = random.nextInt(4);
    switch (parenthesisFlag) {
      case 0:
      case 1:
      case 2:
        object[0] = false;
        object[1] = parenthesis;
        break;
      case 3:
        if (quitCheck) {
          object[0] = false;
          object[1] = parenthesis;
          break;
        }
        parenthesis++;
        object[0] = true;
        object[1] = parenthesis;
        stringBuilder.append("(");
        break;
      default:
        object[0] = false;
        object[1] = parenthesis;
        break;
    }
    return object;
  }

  /**
   * Add closing bracket.
   *
   * @param parenthesis     Number of parenthesis added
   * @param stringBuilder   changed buffer
   * @param leftParenthesis Number of left parenthesis added
   * @return int
   */
  // 添加右括号)
  public int appendRightParenthesis(int parenthesis,
                                    boolean leftParenthesis, StringBuilder stringBuilder) {
    Random random = new Random();
    int rightParenthesis = random.nextInt(2);
    if (!leftParenthesis && parenthesis > 0 && rightParenthesis == 1) {
      stringBuilder.append(")");
      parenthesis--;
    }
    return parenthesis;
  }

  /**
   * Close and complete the right bracket.
   *
   * @param parenthesis   Number of parenthesis added
   * @param stringBuilder changed buffer
   */
  // 闭合，补全右括号)
  public void closeParenthesis(int parenthesis, StringBuilder stringBuilder) {
    for (int j = 0; j < parenthesis; j++) {
      stringBuilder.append(")");
    }
  }

  /**
   * Add random root sign.
   *
   * @param stringBuilder      changed buffer
   * @param evolutionAndSquare indicates whether to add the root sign
   * @return The return value indicates whether to add the root sign
   */
  // 随机添加根号
  public Boolean appendEvolution(StringBuilder stringBuilder, Boolean evolutionAndSquare) {
    JuniorPaperGenerator junior = new JuniorPaperGenerator();
    Random random = new Random();
    // 随机加根号
    int evolutionFlag = random.nextInt(4);
    if (evolutionFlag == 0) {
      stringBuilder.append(junior.getJuniorSymbol().get(1));
      evolutionAndSquare = true;
    } else {
      evolutionAndSquare = false;
    }
    return evolutionAndSquare;
  }

  /**
   * Randomly add squares.
   *
   * @param stringBuilder      changed buffer
   * @param evolutionAndSquare indicates whether to add the root sign
   */
  // 随机添加平方
  public void appendSquare(StringBuilder stringBuilder, Boolean evolutionAndSquare, int operateNum) {
    JuniorPaperGenerator junior = new JuniorPaperGenerator();
    Random random = new Random();
    int squareFlag = random.nextInt(2);
    if (squareFlag == 0 || (evolutionAndSquare == false && operateNum <= 3)) {
      stringBuilder.append(junior.getJuniorSymbol().get(0));
    }
  }

  /**
   * Add random trigonometric functions.
   *
   * @param triNum        Numbering using trigonometric function symbols
   * @param stringBuilder changed buffer
   * @return int return numbering using trigonometric function symbols
   */
  // 随机添加三角函数
  public int appendTriFunction(int triNum, StringBuilder stringBuilder) {
    SeniorPaperGenerator senior = new SeniorPaperGenerator();
    Random random = new Random();
    // 随机生成三角函数
    stringBuilder.append(senior.getSeniorSymbol().get(triNum));
    triNum = random.nextInt(4);
    return triNum;
  }

}
