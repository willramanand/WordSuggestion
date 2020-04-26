package io.github.willramanand;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {
    Path messagesPath = Paths.get("./src/messages.txt");

    // Create a map based bigram.
    MapReduce mr = new MapReduce();
    Map<Set<String>, Integer> sortedBag = mr.mapReduce(messagesPath);

    // Collect the frequency of each word.
    Frequency frequency = new Frequency();
    Map<String, Integer> sortedWordFrequency = frequency.findFrequency(messagesPath);

    // Perform affinity analysis on the document
    AffinityAnalysis affinityAnalysis = new AffinityAnalysis();
    ArrayList<PairInfo> storedPairInfo = affinityAnalysis
        .analyze(sortedBag, sortedWordFrequency);

    Scanner sc = new Scanner(System.in);
    String word = "";
    ArrayList<String> suggestedWords = new ArrayList<>();

    // Loop for suggesting words
    while (!word.equalsIgnoreCase("exit")) {
      System.out.println("Please enter a word or type exit to quit program: ");
      word = sc.next(); // input

      // if user types exit, exit the system.
      if (word.equals("exit")) {
        System.exit(0);
      }

      // Suggest the words
      WordSuggestion ws = new WordSuggestion();
      suggestedWords = ws.suggestWords(word, storedPairInfo);

      // Output the suggestions
      for (int i = 0; i <= suggestedWords.size() - 1; i++) {
        System.out.println("Your next word might be " + suggestedWords.get(i));
      }
    }
  }
}
