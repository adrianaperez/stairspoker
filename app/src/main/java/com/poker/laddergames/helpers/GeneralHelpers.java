package com.poker.laddergames.helpers;

import java.util.Arrays;
import java.util.List;

public class GeneralHelpers {
    // Build string of hand string
    public static String buildStringHand(int[] hand){
        String cards = "[";
        for (int i = 0; i < hand.length; i++) {
            if(i == hand.length - 1)
                cards += hand[i] + "]";
            else
                cards += hand[i] + ",";
        }
        return cards;
    }

    // Determines whether a card game in a list represents a poker straight
    public static boolean isStairs(int[] hand) {
        int[] cards = removeDuplicates(hand);

        if (cards.length < 5)
            return false;

        Arrays.sort(cards);

        for (int i = 0; i < cards.length; i++) {
            int counter = 1;

            if(cards[i] == 2 && cards[cards.length - 1] == 14)
                counter++;

            for (int j = i + 1; j < cards.length; j++) {
                if(cards[j] != cards[j-1] + 1)
                    break;

                counter++;
            }

            if (counter >= 5)
                return true;
        }
        return false;
    }

    // Remove duplicate elements from an array
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .toArray();
    }

    // Convert Integer List to int Array
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
            ret[i] = integers.get(i);

        return ret;
    }
}
