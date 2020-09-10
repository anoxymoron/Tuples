package com.anoxymoron.tuples;

import java.util.HashSet;
import java.util.List;

/**
 * Created by jonathan on 11/12/18.
 */

public class CardSet<AbstractCard> extends HashSet {

    public CardSet<AbstractCard> addCards(int numCardsToAdd, List<AbstractCard> potentialCards) {
        for(int i = 0; i<numCardsToAdd; i++) {
            int seed;
            AbstractCard toAdd;

            do {
                seed = (int) (Math.random() * potentialCards.size());
                toAdd = potentialCards.get(seed);
            } while(this.contains(toAdd)); // don't add the card if it's already displayed

            this.add(toAdd);
        }

        return this;
    }
}
