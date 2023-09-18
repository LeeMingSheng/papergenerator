package papergenerator.paper;

import java.io.File;
import java.util.TreeMap;

/**
 * Abstract class for making test papers.
 */
public abstract class PaperGenerator {
  /**
   * Get the collection of primary school symbols.
   */
  //小学符号
  protected TreeMap<Integer, String> symbolForPrimary = new TreeMap<>();
  ProblemGenerator problemGenerator = new ProblemGenerator();

  /**
   * Call the method to generate test papers based on the number of questions.
   *
   * @param n         The number of questions generated
   * @param freshFile File written
   */
  public abstract void generatePaper(int n, File freshFile);

  /**
   * Check if there are duplicate questions.
   *
   * @param file    File to store questions
   * @param problem Pass in the question to be compared
   * @return boolean The return value indicates whether the question is repeated
   */
  public abstract boolean repetitionCheck(File file, String problem);

  /**
   * Constructor for PaperGenerator.
   */
  public PaperGenerator() {}

}
