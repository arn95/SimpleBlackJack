package com.arnoldballiu.simpleblackjack.object;

import com.arnoldballiu.simpleblackjack.R;

/**
 * Created by arnb on 11/1/17.
 */

public class Card {

    /**
     * Number on the card. From 1 to 10. For jack, queen and king is from 11 to 13 respectively
     */
    private int num = -1;
    /**
     * The id of the drawable that is going to loaded to represent this card object visually on the UI
     */
    private int drawableId = -1;
    /**
     * The constant id to represent type of card. Ranges from 0 to 3.
     * It can be <code>DIAMONDS</code>, <code>HEARTS</code>, <code>CLUBS</code>, <code>SPADES</code>
     */
    private int type = -1;
    /**
     * The human name of the card. Ex. Ace of Spades, Two of Hearts etc.
     */
    private String name = "";
    /**
     * The value of the card.
     * In blackjack the values are 2-10 to cards 2-10.
     * For Jack, Queen and King the value will be 10
     * For Ace it can be either 1 or 11 depending on what is more favorable
     */
    private int value = -1;

    /**
     * Type constants to be used to set <code>type</code> in a card object
     */
    public static final int DIAMONDS = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;

    /**
     * Private constructor to prevent call.
     * Needs a <code>num</code> for card rank and <code>type</code> for card type
     *
     * @param num The rank of the card to create
     * @param type The type of the card to create
     */
    private Card(int num, int type){
        this.num = num;
        this.type = type;
        this.drawableId = getDrawable();
        generateName();
    }

    /**
     * Static method to initialize the card object and carry out pre-use operations
     *
     * @param num The rank of the card to create
     * @param type The type of the card to create
     * @return
     */
    public static Card init(int num, int type){
        return new Card(num, type);
    }

    ///////////////////////////////////////////////
    ///
    /// GETTERS AND SETTERS FOR FIELDS
    ///
    ///////////////////////////////////////////////
    public void setNum(int num) {
        this.num = num;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    ///////////////////////////////////////////////

    /**
     * Generates a human readable name for the card and sets the <code>name</code> variable
     * It uses the card <code>num</code> and <code>type</code> to determine name
     */
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
                case 11: this.name = "Jack"; break;
                case 12: this.name = "Queen"; break;
                case 13: this.name = "King"; break;
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

    /**
     * Returns the specific drawable id that pertains to a png of the card during runtime.
     * It depends on <code>type</code> and <code>num</code> to find the right drawable
     * @return The drawable id that this card uses to be displayed on UI
     */
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
                        case Card.CLUBS : return R.drawable.ten_of_clubs;
                        case Card.DIAMONDS: return R.drawable.ten_of_diamonds;
                        case Card.HEARTS: return R.drawable.ten_of_hearts;
                        case Card.SPADES: return R.drawable.ten_of_spades;
                        default: return -1;
                    }

                }

                case 11: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.jack_of_clubs;
                        case Card.DIAMONDS: return R.drawable.jack_of_diamonds;
                        case Card.HEARTS: return R.drawable.jack_of_hearts;
                        case Card.SPADES: return R.drawable.jack_of_spades;
                        default: return -1;
                    }

                }

                case 12: {

                    switch (type){
                        case Card.CLUBS : return R.drawable.queen_of_clubs;
                        case Card.DIAMONDS: return R.drawable.queen_of_diamonds;
                        case Card.HEARTS: return R.drawable.queen_of_hearts;
                        case Card.SPADES: return R.drawable.queen_of_spades;
                        default: return -1;
                    }

                }

                case 13: {

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
