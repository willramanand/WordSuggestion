package io.github.willramanand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frequency {

  /**
   * Returns a map containing the frequency with which each word is said.
   *
   * @param path the file location
   * @return Map contain a word and the amount of times it is used.
   * @throws IOException thrown if file not found.
   */
  public Map findFrequency(Path path) throws IOException {
    Map<String, Integer> wordFrequency = new HashMap<>();
    LinkedHashMap<String, Integer> sortedWordFrequency = new LinkedHashMap<>();

    // Read all lines (except empty) into a String Stream for later processing
    Stream<String> messagesLines = Files.lines(path)
        .filter(line -> !line.isEmpty());

    // Then amend our earlier example by adding a split & flatMapping our Arrays
    List<String> messagesWords = messagesLines
        .map(String::toLowerCase)
        .map(line -> line.split("\\s+"))
        .flatMap(Arrays::stream)
        .collect(Collectors.toList());

    for (int i = 0; i < messagesWords.size(); i++) {
      wordFrequency.merge(messagesWords.get(i), 1, Integer::sum);
    }

    wordFrequency.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> sortedWordFrequency.put(x.getKey(), x.getValue()));

    return sortedWordFrequency;
  }

}
