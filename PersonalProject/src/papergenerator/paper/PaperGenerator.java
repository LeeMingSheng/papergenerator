package papergenerator.paper;

import java.io.File;
import java.util.TreeMap;

public abstract class PaperGenerator {
  //小学符号
  protected TreeMap<Integer, String> symbolForPrimary = new TreeMap<>();
  ProblemGenerator problemGenerator = new ProblemGenerator();

  public abstract void generatePaper(int n, File freshFile);

  public abstract boolean repetitionCheck(File file, String problem);

  public PaperGenerator() {}

}
