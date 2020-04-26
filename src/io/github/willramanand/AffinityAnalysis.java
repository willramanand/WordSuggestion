package io.github.willramanand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class AffinityAnalysis {

  /**
   * The class performs affinity analysis on a map based bigram.
   *
   * @param sortedBag           sorted map of pairs and the frequency of those pairs.
   * @param sortedWordFrequency sorted map of words and the frequency of those words.
   * @return an ArrayList containing PairInfo
   */
  public ArrayList analyze(Map<Set<String>, Integer> sortedBag,
      Map<String, Integer> sortedWordFrequency) {
    // Initialize return ArrayList
    ArrayList<PairInfo> storedPairInfo = new ArrayList<>();

    // Initialize all the variables
    int totalWords = 0;
    int pairFrequency = 0;
    int wordFrequency = 0;
    double confidence = 0;
    double support = 0;

    // Get total words in file
    for (Map.Entry<String, Integer> entry : sortedWordFrequency.entrySet()) {
      totalWords = totalWords + entry.getValue();
    }

    Map<Set<String>, Set<Double>> affinityPairs = new HashMap<>();

    /* For each word look through each pair until you find that word
    then get the pair frequency and word frequency and calculate
    confidence */
    for (Map.Entry<String, Integer> wordEntry : sortedWordFrequency.entrySet()) {
      for (Map.Entry<Set<String>, Integer> pairEntry : sortedBag.entrySet()) {
        for (Iterator<String> it = pairEntry.getKey().iterator(); it.hasNext(); ) {
          String wordFound = it.next();
          if (wordFound.equals(wordEntry.getKey())) {
            // Get word and pair frequency
            pairFrequency = pairEntry.getValue();
            wordFrequency = wordEntry.getValue();

            // Calculate confidence and support
            confidence = (double) pairFrequency / totalWords;
            support = (double) pairFrequency / wordFrequency;

            // Add values to Storage Class and add Storage Class to Array List
            storedPairInfo.add(new PairInfo(pairEntry.getKey(), support, confidence));
          }
        }
      }
    }
    return storedPairInfo;
  }
}
