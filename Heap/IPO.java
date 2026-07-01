package Heap;

import java.util.PriorityQueue;

class Share {

    int capital;
    int profit;

    Share(int capital, int profit) {
        this.capital = capital;
        this.profit = profit;
    }
}

public class IPO {

    public int findMaximizedCapital(
        int k,
        int w,
        int[] profits,
        int[] capital
    ) {
        PriorityQueue<Share> capitalHeap = new PriorityQueue<>((a, b) ->
            Integer.compare(a.capital, b.capital)
        );
        PriorityQueue<Share> profitHeap = new PriorityQueue<>((a, b) ->
            Integer.compare(b.profit, a.profit)
        );
        int maximumCapital = w;

        for (int i = 0; i < profits.length; i++) {
            Share share = new Share(capital[i], profits[i]);

            capitalHeap.offer(share);
        }

        for (int i = 0; i < k; i++) {
            while (maximumCapital <= capitalHeap.peek().capital) {
                profitHeap.offer(capitalHeap.poll());
            }

            if (profitHeap.isEmpty()) break;

            maximumCapital += profitHeap.poll().profit;
        }

        return maximumCapital;
    }
}
