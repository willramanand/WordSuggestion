package io.github.willramanand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapReduce {

  /**
   * Takes in a file path and collects and sorts the pairs of words in that file and their amount of
   * usages.
   *
   * @param path file location
   * @return Map holding the sorted set of strings and the amount of instances that pair shows up.
   * @throws IOException if the path is invalid and no file is parsed
   */
  public Map mapReduce(Path path) throws IOException {

    // Read all lines (except empty) into a String Stream for later processing
    Stream<String> messagesLines = Files.lines(path)
        .filter(line -> !line.isEmpty());

    // Then amend our earlier example by adding a split & flatMapping our Arrays
    List<String> messagesWords = messagesLines
        .map(String::toLowerCase)
        .map(line -> line.split("\\s+"))
        .flatMap(Arrays::stream)
        .collect(Collectors.toList());

    Map<Set<String>, Integer> bGrams = new HashMap<>();

    // Insert values into bigram
    for (int i = 1; i < messagesWords.size(); i++) {
      bGrams.merge(new HashSet<>(Arrays.asList(
          messagesWords.get(i - 1),
          messagesWords.get(i))),
          1, Integer::sum);
    }

    // Sort in value (highest to lowest) using stream (Functional Programming)
    Map<Set<String>, Integer> sortedBag = new HashMap<>();
    sortedBag = bGrams.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new));

    return sortedBag;
  }

}
