package io.github.willramanand;

import java.util.ArrayList;


public class WordSuggestion {

  /**
   * Suggest words based on inputted word and array list of pairs and their info.
   *
   * @param word           inputted word.
   * @param storedPairInfo ArrayList of the PairInfo.
   * @return ArrayList of word suggestions.
   */
  public ArrayList suggestWords(String word, ArrayList<PairInfo> storedPairInfo) {
    ArrayList<String> suggestions = new ArrayList<>();

    // Loops through arrayList find if the word matches and its pair to suggest.
    for (int i = 0; i <= storedPairInfo.size() - 1; i++) {
      if (storedPairInfo.get(i).getSupport() >= 0.65) {
        if (word.equalsIgnoreCase(storedPairInfo.get(i).getWordOne()) && !suggestions
            .contains(storedPairInfo.get(i).getWordTwo())) {
          suggestions.add(storedPairInfo.get(i).getWordTwo());
        } else if (word.equalsIgnoreCase(storedPairInfo.get(i).getWordTwo()) && !suggestions
            .contains(storedPairInfo.get(i).getWordOne())) {
          suggestions.add(storedPairInfo.get(i).getWordOne());
        }
      }
    }

    // If the list is smaller than three pad it with most common connectors.
    if (suggestions.size() < 3) {
      suggestions.add("the");
      suggestions.add("this");
      suggestions.add("of");
    }
    return suggestions;
  }
}
