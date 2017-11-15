package com.arnoldballiu.simpleblackjack.object;

import java.util.ArrayList;

/**
 * Created by arnb on 11/1/17.
 */

public class Dealer {


    /**
     * Auto-init a deck object to be used after object initialized
     */
    private Deck deck = Deck.init();
    /**
     * Auto init an arraylist to hold the cards. Simulates the dealers hand of cards.
     */
    private ArrayList<Card> dealerHand = new ArrayList<>();
    /**
     * The total value of the cards held by the dealer
     */
    private int handTotal = 0;
    /**
     * Indicates whether the dealer holds an ace in his hand
     */
    private boolean hasAce = false;
    /**
     * Indicates whether the dealer holds a 10 point card in his hand
     */
    private boolean has10PointCard = false;

    /**
     * Private constructor to prevent premature use.
     */
    private Dealer(){

    }

    /**
     * Static method to initialize a <code>Dealer</code> object and carry out
     * some pre-use operations such as drawing 2 cards for itself.
     * @return A <code>Dealer</code> object
     */
    public static Dealer init(){
        Dealer dealer = new Dealer();
        dealer.draw();
        dealer.draw();
        return dealer;
    }

    /**
     * Draws a card from the deck and returns it to the caller
     * @return A new card straight from the deck
     */
    public Card deal(){
        return deck.draw();
    }

    /**
     * Getter method to return a card from the dealers hand when given an index.
     * Used primarily to find the first and second card in dealers hand for early game operations
     *
     * @param index The index where the card needed is in
     * @return The card in the given <code>index</code>
     */
    public Card getCard(int index){
        return dealerHand.get(index);
    }

    /**
     * Gets a card from the deck and adds it to the dealers hand.
     * Keeps track of the <code>handTotal</code> value.
     * Based on the current <code>handTotal</code> value the Aces will either be 1 or 11
     * Sets <code>hasAce</code> or <code>has10PointCard</code> when necessary
     *
     * @return An instance of the newly drawn card from the deck that is added to the dealers hand.
     */
    public Card draw(){
        Card card = deck.draw();

        //if this is an ace. finding out what value to use
        if (card.getNum() == 1){
            this.hasAce = true;
            if (handTotal + 11 > 21)
                handTotal += card.getValue(); //using 1
            else {
                card.setValue(11);
                handTotal += 11; //using 11
            }
        } else {
            handTotal += card.getValue();
        }

        if (card.getNum() > 10)
            this.has10PointCard = true;

        dealerHand.add(card);
        return card;
    }

    /**
     * Determines if the dealer has a blackjack.
     * A blackjack is an Ace paired with a Jack, Queen or King card (10 point cards)
     * @return True if blackjack or false if not
     */
    public boolean hasBlackJack() {
        return hasAce && has10PointCard;
    }

    /**
     * Gives the total value of cards in the dealers hand
     * @return An int value representing the total value of cards in dealers hand
     */
    public int getHandTotal(){
        return handTotal;
    }


}
