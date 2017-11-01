package com.arnoldballiu.simpleblackjack.object;

import java.util.ArrayList;

/**
 * Created by arnb on 11/1/17.
 */

public class Player {

    private ArrayList<Card> playerHand = new ArrayList<>();

    private Player(){

    }

    public static Player init(Card firstCard, Card secondCard){
        Player player = new Player();
        player.playerHand.add(firstCard);
        player.playerHand.add(secondCard);
        return player;
    }

    public Card getCard(int index){
        return playerHand.get(index);
    }

    public Card draw(Card card){
        playerHand.add(card);
        return card;
    }
}
