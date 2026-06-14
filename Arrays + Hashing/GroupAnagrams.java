// Given an array of strings strs,
// group the anagrams together.
// You can return the answer in any order.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] key = str.toCharArray();
            Arrays.sort(key);

            String sortedKey = new String(key);
            List<String> group = map.get(sortedKey);

            if (group != null) {
                group.add(str);
                continue;
            }

            List<String> newList = new ArrayList<>();
            newList.add(str);
            map.put(sortedKey, newList);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> result = groupAnagrams(strs);

        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}
