package com.anoxymoron.tuples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TuplesMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuples_main);

        TableRow firstRow = findViewById(R.id.firstRow);
        TableRow secondRow = findViewById(R.id.secondRow);
        TableRow thirdRow = findViewById(R.id.thirdRow);

        List<StandardCard> potentialCards = StandardCard.generateAllCards("simple");
        CardSet<StandardCard> currentCards = new CardSet<>();
        int numCardsToAdd = 12;

        currentCards.addCards(numCardsToAdd, potentialCards);
        Iterator<AbstractCard> iterator = currentCards.iterator();

        int cardNum = 0;

        while(iterator.hasNext()) {
            StandardCard c = (StandardCard) iterator.next();
            Button btn = new Button(this);
            String text = "N: " + String.valueOf(c.getNumber()) + "\n" +
                    "C: " + c.getColor() + "\n" +
                    "S: " + c.getShape() + "\n" +
                    "T: " + c.getTexture();

            btn.setId(View.generateViewId());
            btn.setText(text);
            btn.setTextSize(12);
            btn.setGravity(Gravity.START | Gravity.TOP);
            btn.setHeight(firstRow.getHeight());

            TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            p.weight = 1;
            btn.setLayoutParams(p);
            //Log.d("cardInfo", text);

            if(cardNum < 4) {
                firstRow.addView(btn);
            } else if(cardNum < 8) {
                secondRow.addView(btn);
            } else {
                thirdRow.addView(btn);
            }
            cardNum++;
        }
    }

    public static Set<StandardCard> dealCards(int numCards, Set<StandardCard> currentCards, List<StandardCard> potentialCards) {
        for(int i = 0; i<numCards; i++) {
            int seed = (int) (Math.random() * potentialCards.size());
            StandardCard toAdd = potentialCards.get(seed);

            // don't add the card if it's already displayed
            while(currentCards.contains(toAdd)) {
                seed = (int) (Math.random() * potentialCards.size());
                toAdd = potentialCards.get(seed);
            }

            currentCards.add(toAdd);
        }

        return currentCards;
    }
}
