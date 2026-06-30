// Given a string s, rearrange the characters
// of s so that any two adjacent characters
// are not the same. Return any possible
// rearrangement of s or return "" if not possible.

package Heap;

import java.util.HashMap;
import java.util.PriorityQueue;

class FrequencyStore {

    char c;
    int frequency;

    FrequencyStore(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }
}

public class ReorganizeString {

    public String reorganizeString(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> charFreqMap = new HashMap<>();
        PriorityQueue<FrequencyStore> maxHeap = new PriorityQueue<>((a, b) ->
            Integer.compare(b.frequency, a.frequency)
        );
        FrequencyStore previousCharacter = null;

        for (char c : s.toCharArray()) {
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }

        for (char c : charFreqMap.keySet()) {
            FrequencyStore f = new FrequencyStore(c, charFreqMap.get(c));
            maxHeap.offer(f);
        }

        while (!maxHeap.isEmpty()) {
            FrequencyStore f = maxHeap.poll();
            sb.append(f.c);
            f.frequency--;

            if (
                previousCharacter != null && previousCharacter.frequency > 0
            ) maxHeap.offer(previousCharacter);

            previousCharacter = f;
        }

        return sb.toString();
    }
}
