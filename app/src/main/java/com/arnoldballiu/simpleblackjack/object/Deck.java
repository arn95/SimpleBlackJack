package com.arnoldballiu.simpleblackjack.object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by arnb on 11/1/17.
 */

public class Deck {

    private ArrayList<Card> cardList;

    private Deck(ArrayList<Card> cardList){
        this.cardList = cardList;

        for (int num = 1; num<=12; num++){
            for (int type = 0; type<4; type++){
                cardList.add(Card.init(num, type));
            }
        }

        Collections.shuffle(this.cardList);
    }

    public static Deck init(){
        return new Deck(new ArrayList<Card>());
    }

    public void shuffle(){
        Collections.shuffle(this.cardList);
    }

    public Card draw(){
        return cardList.remove(0);
    }
}
