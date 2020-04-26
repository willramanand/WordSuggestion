package io.github.willramanand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class PairInfo {

  private ArrayList<String> wordArray;
  private String wordOne;
  private String wordTwo;
  private double support;
  private double confidence;

  /**
   * Constructor for PairInfo class.
   *
   * @param wordPairs  Set of Strings that contain the word pair.
   * @param support    the calculated support of that pair.
   * @param confidence the calculate confidence of that pair.
   */
  public PairInfo(Set<String> wordPairs, double support, double confidence) {
    for (Iterator<String> it = wordPairs.iterator(); it.hasNext(); ) {
      wordOne = it.next();
      wordTwo = it.next();
    }
    this.support = support;
    this.confidence = confidence;
  }

  /**
   * ACCESSORS
   **/
  public String getWordOne() {
    return wordOne;
  }

  public String getWordTwo() {
    return wordTwo;
  }

  public double getSupport() {
    return support;
  }

  public double getConfidence() {
    return confidence;
  }

  /**
   * Custom toString method for printing out info in this class
   *
   * @return String containing the word pairs and their confidence and support.
   */
  @Override
  public String toString() {
    return wordOne
        + ", " + wordTwo
        + " : confidence = " + confidence
        + ", support = " + support;
  }
}
