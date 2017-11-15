package com.arnoldballiu.simpleblackjack.object;

import java.util.ArrayList;

/**
 * Created by arnb on 11/1/17.
 */

public class Player {

    /**
     * Represents the hand of the player.
     */
    private ArrayList<Card> playerHand = new ArrayList<>();
    /**
     * The total value of the cards held by the player
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
    private Player(){

    }

    /**
     * Initializes the <code>Player</code> object and runs pre-use operations to get the object ready
     *
     * @param dealer Dealer object needed to get the first two cards from the deck. \
     * The dealer is supposed to deal the cards and the dealer here represents that function
     * @return The <Code>Player</Code> object that is ready for use by the caller.
     */
    public static Player init(Dealer dealer){
        Player player = new Player();
        player.draw(dealer.deal());
        player.draw(dealer.deal());
        return player;
    }

    /**
     * Gets a card from the players hand in the specified <code>index</code>
     *
     * @param index The position of the card that the caller requires
     * @return The <code>Card</code> object the caller needs specified by <code>index</code>
     */
    public Card getCard(int index){
        return playerHand.get(index);
    }

    /**
     * Gets a card from the deck and adds it to the players hand.
     * Keeps track of the <code>handTotal</code> value.
     * Based on the current <code>handTotal</code> value the Aces will either be 1 or 11
     * Sets <code>hasAce</code> or <code>has10PointCard</code> when necessary
     * A listener <code>listener</code> object can be provided to notify when the card
     * has been drawn (used only in UI operations when order matters for aesthetics)
     *
     * @return An instance of the newly drawn card from the deck that is added to the players hand.
     */
    public Card draw(Card card, PlayerListener listener){

        //if this is an ace. finding out what value to use
        if (card.getNum() == 1){
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

        playerHand.add(card);
        listener.onDraw(this.handTotal);
        return card;
    }

    /**
     * Gets a card from the deck and adds it to the players hand.
     * Keeps track of the <code>handTotal</code> value.
     * Based on the current <code>handTotal</code> value the Aces will either be 1 or 11
     * Sets <code>hasAce</code> or <code>has10PointCard</code> when necessary
     * Overloaded version of the same <code>draw()</code> method without the <code>PlayerListener</code> object
     *
     * @return An instance of the newly drawn card from the deck that is added to the players hand.
     */
    public Card draw(Card card){

        //if this is an ace. finding out what value to use
        if (card.getNum() == 1){
            this.hasAce = true;
            if (handTotal + 11 > 21) //Using 11 would be disadvantageous
                //card already has the value of 1
                handTotal += card.getValue(); //using 1
            else {
                card.setValue(11); //Setting the value to the card
                handTotal += 11; //using 11
            }
        }
        else { //if not an ace
            handTotal += card.getValue();
        }

        if (card.getNum() > 10)
            this.has10PointCard = true;

        playerHand.add(card);
        return card;
    }

    /**
     * Determines if the dealer has a blackjack.
     * A blackjack is an Ace paired with a Jack, Queen or King card (10 point cards)
     * @return True if blackjack or false if not
     */
    public boolean hasBlackJack(){
        return hasAce && has10PointCard;
    }

    /**
     * Gives the total value of cards in the dealers hand
     * @return An int value representing the total value of cards in dealers hand
     */
    public int getHandTotal(){
        return handTotal;
    }

    /**
     * Interface to act as a listener object. Used in card operations where order of drawing matters and correlates with UI events.
     */
    public interface PlayerListener {
        /**
         * Called by the publisher to notify the subscribers that the card has been drawn.
         * Provides the subscribers with the total value of the cards in players hand
         *
         * @param handTotal An int provided to the subscribers from the publishers that represents the
         * total value of the cards in the players hand.
         */
        void onDraw(int handTotal);
    }
}
