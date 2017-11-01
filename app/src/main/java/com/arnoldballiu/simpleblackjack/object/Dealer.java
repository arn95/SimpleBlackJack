package com.arnoldballiu.simpleblackjack.object;

import java.util.ArrayList;

/**
 * Created by arnb on 11/1/17.
 */

public class Dealer {


    private Deck deck = Deck.init();

    private ArrayList<Card> dealerHand = new ArrayList<>();

    private Dealer(){
        dealerHand.add(deck.draw());
    }

    public static Dealer init(){
        Dealer dealer = new Dealer();
        dealer.dealerHand.add(dealer.deck.draw());
        dealer.dealerHand.add(dealer.deck.draw());
        return dealer;
    }

    public Card deal(){
        return deck.draw();
    }

    public Card getCard(int index){
        return dealerHand.get(index);
    }

    public Card draw(){
        Card card = deck.draw();
        dealerHand.add(card);
        return card;
    }


}
