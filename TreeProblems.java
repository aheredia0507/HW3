/*
 * *** Arthur Heredia / COMP 272 002 F25 ***
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

import java.util.*;

public class TreeProblems {

  /**
   * Method different()
   *
   * Given two TreeSets of integers, return a TreeSet containing all elements
   * that are NOT in both sets. In other words, return a TreeSet of all the
   * elements that are in one set but not the other.
   *
   * -------------------------------------------------------------------------------------------------------------------
   *
   * Notes:
   *   - TreeSet automatically keeps elements sorted
   *   - Hint said to use retainAll, addAll, and removeAll with temporary sets
   *
   * Pseudocode:
   *
   * 1. Make a TreeSet copy of setA
   *
   * 2. Make a TreeSet copy of setB
   *
   * 3. Find the intersection (things in both) by:
   *     - copyA.retainAll(copyB)
   *
   * 4. Make a union set (everything from setA and setB)
   *
   * 5. Remove the intersection from the union
   *
   * 6. Return what’s left
   *
   */

  public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
      Set<Integer> copyA = new TreeSet<>(setA);    // 1. Copy setA

      Set<Integer> copyB = new TreeSet<>(setB);    // 2. Copy setB

      Set<Integer> intersection = new TreeSet<>(copyA);    // 3. Find the intersection
      intersection.retainAll(copyB);   // keeps only values found in both

      Set<Integer> union = new TreeSet<>(copyA);    // 4. Make the union (everything in A and B)
      union.addAll(copyB);

      union.removeAll(intersection);     // 5. Subtract the intersection, leaves only unique stuff

      return union;    // 6. Return the difference
  }


  /**
   * Method removeEven()
   *
   * Given a treeMap with the key as an integer, and the value as a String,
   * remove all <key, value> pairs where the key is even.
   *
   * -------------------------------------------------------------------------------------------------------------------
   *
   * Pseudocode:
   *
   * 1. If the map is null or empty, stop
   *
   * 2. Get an iterator over entrySet() so we can safely remove while iterating
   *
   * 3. While there are more entries:
   *     - look at the key
   *     - if key % 2 == 0, this means the key is even (divides evenly by 2 with no remainder)
   *       so remove it using iterator.remove()
   *
   */

  public static void removeEven(Map<Integer, String> treeMap) {
      if (treeMap == null || treeMap.isEmpty()) return;    // 1. stop if map is null/empty

      // 2. Use iterator over entries so we can safely remove while looping
      Iterator<Map.Entry<Integer, String>> it = treeMap.entrySet().iterator();

      while (it.hasNext()) {   // 3. Walk entries; remove those with even keys
          Map.Entry<Integer, String> entry = it.next();    // 3a. look at current <k,v>
          Integer key = entry.getKey();                    // 3b. get key
          if (key % 2 == 0) {                              // 3c. even key
              it.remove();                                 // remove safely via iterator
          }
      }
  }


  /**
   * Method treesEqual()
   *
   * Given two treeMaps, each with the key as an integer, and the value as a String,
   * return a boolean value indicating if the two trees are equal or not.
   *
   * -------------------------------------------------------------------------------------------------------------------
   *
   * Pseudocode:
   *
   * 1. If both maps are null, return true
   *
   * 2. If one is null and the other isn’t, return false
   *
   * 3. If sizes are different, return false
   *
   * 4. For each key in the first map:
   *    - if the second map doesn’t have the key, return false
   *    - if values for that key don’t match, return false
   *
   * 5. If we finish the loop with no differences, return true
   *
   */

  public boolean treesEqual(Map<Integer, String> tree1,Map<Integer, String> tree2 ) {
      // 1. If both are the same reference, they are equal
      if (tree1 == tree2) {    // includes (null == null)
          return true;
      }

      // 2. If one is null but the other isn’t, not equal
      if (tree1 == null || tree2 == null) {    // one null, one not null
          return false;
      }

      // 3. If sizes differ, they can’t be equal
      if (tree1.size() != tree2.size()) {    // different number of entries
          return false;
      }

      // 4. Walk every <key,value> in the first map and compare with the second
      for (Map.Entry<Integer, String> entry : tree1.entrySet()) {
          Integer key = entry.getKey();    // 4a. current key
          String v1 = entry.getValue();    // 4b. value from first map

          if (!tree2.containsKey(key)) {   // 4c. key must also exist in the second map
              return false;
          }

          String v2 = tree2.get(key);      // 4d. value from second map

          if (v1 == null) {                // 4e. compare values
              if (v2 != null){    // one is null, the other isn’t
                  return false;
              }
          }

          else {
              if (!v1.equals(v2)) {    // both non-null: use equals()
                  return false;
              }
          }

      }
      // 5. All keys matched and all values matched
      return true;
  }

} // end treeProblems class
