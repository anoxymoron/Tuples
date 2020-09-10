package com.anoxymoron.tuples;

import java.util.List;
import java.util.Set;

/**
 * Created by jonathan on 12/22/17.
 */

public abstract class AbstractCard {
    // generate a set of cards to display from a given deck
    /*
    public static Set<AbstractCard> dealCards(int numCards, Set<? extends AbstractCard> currentCards, List<? extends AbstractCard> potentialCards) {
        for(int i = 0; i<numCards; i++) {
            int seed = (int) (Math.random() * potentialCards.size());
            AbstractCard toAdd = potentialCards.get(seed);

            // don't add the card if it's already displayed
            while(currentCards.contains(toAdd)) {
                seed = (int) (Math.random() * potentialCards.size());
                toAdd = potentialCards.get(seed);
            }

            currentCards.add(toAdd);
        }

        return currentCards;
    }
    */
}
