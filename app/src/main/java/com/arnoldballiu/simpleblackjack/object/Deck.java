package com.arnoldballiu.simpleblackjack.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by arnb on 11/1/17.
 */

public class Deck {

    /**
     * Simulates the deck. Created when object is initialized.
     */
    private List<Card> cardList = new ArrayList<>();

    /**
     * Private constructor to prevent calling prematurely.
     */
    private Deck(){
        for (int num = 1; num<=13; num++){ //representing cards from Ace to King.
            for (int type = 0; type<4; type++){ //representing the card types to be assigned to cards from Ace to King.

                Card card = Card.init(num, type);

                if (num > 10) //if jack, queen or king
                    card.setValue(10);
                else
                    card.setValue(num);

                cardList.add(card);
            }
        }

        Collections.shuffle(this.cardList); //shuffle the deck before use
    }

    /**
     * Initializes the deck object and carries pre-use operations.
     * @return The deck object ready for use.
     */
    public static Deck init(){
        return new Deck();
    }

    /**
     * Shuffles the deck aka ArrayList representing the deck <code>cardList</code>
     */
    public void shuffle(){
        Collections.shuffle(this.cardList);
    }

    /**
     * Draws the first card from the deck and provides it to the caller
     * Removes given card from the deck.
     *
     * @return The first card removed and given to the caller.
     */
    public Card draw(){
        return cardList.remove(0);
    }
}
