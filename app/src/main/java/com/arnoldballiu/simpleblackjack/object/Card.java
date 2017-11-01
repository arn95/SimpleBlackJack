package com.arnoldballiu.simpleblackjack.object;

import com.arnoldballiu.simpleblackjack.R;

/**
 * Created by arnb on 11/1/17.
 */

public class Card {

    public int num = -1;
    public int drawableId = -1;
    public int type = -1;
    public String name = "";

    //types
    public static final int DIAMONDS = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;

    private Card(int num, int type){
        this.num = num;
        this.type = type;
        this.drawableId = getDrawable();
        generateName();
    }

    public static Card init(int num, int type){
        return new Card(num, type);
    }

    private void generateName(){

        if (this.num != -1 && this.type != -1){
            switch (this.num){
                case 1: this.name = "Ace"; break;
                case 2: this.name = "Two"; break;
                case 3: this.name = "Three"; break;
                case 4: this.name = "Four"; break;
                case 5: this.name = "Five"; break;
                case 6: this.name = "Six"; break;
                case 7: this.name = "Seven"; break;
                case 8: this.name = "Eight"; break;
                case 9: this.name = "Nine"; break;
                case 10: this.name = "Ten"; break;
                case 11: this.name = "Eleven"; break;
                case 12: this.name = "Twelve"; break;
            }

            this.name += " Of ";

            switch (this.type){
                case CLUBS: this.name += "Clubs"; break;
                case DIAMONDS: this.name += "Diamonds"; break;
                case HEARTS: this.name += "Hearts"; break;
                case SPADES: this.name += "Spades"; break;
            }
        }

    }

    private int getDrawable(){
        if (num != -1 && type != -1){
            switch (num){
                case 1: {
                    switch (type){
                        case Card.CLUBS : return R.drawable.ace_of_clubs;
                        case Card.DIAMONDS: return R.drawable.ace_of_diamonds;
                        case Card.HEARTS: return R.drawable.ace_of_hearts;
                        case Card.SPADES: return R.drawable.ace_of_spades;
                        default: return -1;
                    }
                }

                case 2: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.two_of_clubs;
                        case Card.DIAMONDS: return R.drawable.two_of_diamonds;
                        case Card.HEARTS: return R.drawable.two_of_hearts;
                        case Card.SPADES: return R.drawable.two_of_spades;
                        default: return -1;
                    }

                }

                case 3: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.three_of_clubs;
                        case Card.DIAMONDS: return R.drawable.three_of_diamonds;
                        case Card.HEARTS: return R.drawable.three_of_hearts;
                        case Card.SPADES: return R.drawable.three_of_spades;
                        default: return -1;
                    }

                }

                case 4: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.four_of_clubs;
                        case Card.DIAMONDS: return R.drawable.four_of_diamonds;
                        case Card.HEARTS: return R.drawable.four_of_hearts;
                        case Card.SPADES: return R.drawable.four_of_spades;
                        default: return -1;
                    }

                }

                case 5: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.five_of_clubs;
                        case Card.DIAMONDS: return R.drawable.five_of_diamonds;
                        case Card.HEARTS: return R.drawable.five_of_hearts;
                        case Card.SPADES: return R.drawable.five_of_spades;
                        default: return -1;
                    }

                }

                case 6: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.six_of_clubs;
                        case Card.DIAMONDS: return R.drawable.six_of_diamonds;
                        case Card.HEARTS: return R.drawable.six_of_hearts;
                        case Card.SPADES: return R.drawable.six_of_spades;
                        default: return -1;
                    }

                }

                case 7: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.seven_of_clubs;
                        case Card.DIAMONDS: return R.drawable.seven_of_diamonds;
                        case Card.HEARTS: return R.drawable.seven_of_hearts;
                        case Card.SPADES: return R.drawable.seven_of_spades;
                        default: return -1;
                    }

                }

                case 8: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.eight_of_clubs;
                        case Card.DIAMONDS: return R.drawable.eight_of_diamonds;
                        case Card.HEARTS: return R.drawable.eight_of_hearts;
                        case Card.SPADES: return R.drawable.eight_of_spades;
                        default: return -1;
                    }

                }

                case 9: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.nine_of_clubs;
                        case Card.DIAMONDS: return R.drawable.nine_of_diamonds;
                        case Card.HEARTS: return R.drawable.nine_of_hearts;
                        case Card.SPADES: return R.drawable.nine_of_spades;
                        default: return -1;
                    }

                }

                case 10: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.jack_of_clubs;
                        case Card.DIAMONDS: return R.drawable.jack_of_diamonds;
                        case Card.HEARTS: return R.drawable.jack_of_hearts;
                        case Card.SPADES: return R.drawable.jack_of_spades;
                        default: return -1;
                    }

                }

                case 11: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.queen_of_clubs;
                        case Card.DIAMONDS: return R.drawable.queen_of_diamonds;
                        case Card.HEARTS: return R.drawable.queen_of_hearts;
                        case Card.SPADES: return R.drawable.queen_of_spades;
                        default: return -1;
                    }

                }

                case 12: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.king_of_clubs;
                        case Card.DIAMONDS: return R.drawable.king_of_diamonds;
                        case Card.HEARTS: return R.drawable.king_of_hearts;
                        case Card.SPADES: return R.drawable.king_of_spades;
                        default: return -1;
                    }

                }

                default: return -1;

            }
        } else {
            return -1;
        }

    }

}
