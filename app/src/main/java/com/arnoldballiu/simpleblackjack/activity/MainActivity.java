package com.arnoldballiu.simpleblackjack.activity;

import android.animation.Animator;
import android.app.Dialog;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.arnoldballiu.simpleblackjack.R;
import com.arnoldballiu.simpleblackjack.object.Card;
import com.arnoldballiu.simpleblackjack.object.Dealer;
import com.arnoldballiu.simpleblackjack.object.Player;
import com.arnoldballiu.simpleblackjack.utils.SBJUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Binding UI components with objects
    // Syntactic sugar & annotation language by ButterKnife lib
    @BindView(R.id.hit_btn)
    Button hitBtn;
    @BindView(R.id.stand_btn)
    Button standBtn;
    @BindView(R.id.dealer_hand_board)
    LinearLayout dealerBoardLinearLayout;
    @BindView(R.id.player_hand_board)
    LinearLayout playerBoardLinearLayout;
    @BindView(R.id.dealerTextView)
    TextView dealerTextView;
    @BindView(R.id.playerTextView)
    TextView playerTextView;
    @BindView(R.id.gameStatusText)
    TextView gameStatusText;
    @BindView(R.id.newGameBtn)
    TextView newGameBtn;
    @BindView(R.id.restart_btn)
    Button restartBtn;

    /**
     * Initializing the dealer object during activity load
     */
    private Dealer dealer = Dealer.init();

    /**
     * Initializing the player object with the deal object in order to deal cards for the player object.
     */
    private Player player = Player.init(dealer);

    /**
     * Indicates whether the first setup has been run (dealer deals 2 cards for himself and 2 cards for player)
     */
    private boolean isSetup = false;

    /**
     * Times the player has hit the button "Hit"
     */
    private int playerTimesHit = 0;

    /**
     * Times the dealer has hit
     */
    private int dealerTimesHit = 0;

    /**
     * Constant indicating the max amount of hits the player or dealer can hit
     */
    private static final int MAX_HITS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


        //prevents from setup being run again during orientation change or when activity changes state
        if (!isSetup) { //the player just hit new game and setup hasnt been called
            newGameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newGameBtn.setVisibility(View.GONE);
                    restartBtn.setVisibility(View.VISIBLE);
                    restartBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainActivity.this.recreate();
                            return;
                        }
                    });
                    isSetup = true;
                    setupRound();
                }
            });
        }
    }

    /**
     * Adds the card as a child to the given linear layout by creating an imageview and
     * setting the appropriate params. <code>frontFacing</code> determines if a cards imageview
     * shows the back of the card of the front side of the card. The listener <code>onEnd</code> is passed to an internal structure that
     * notifies the caller of this method when the animation of adding has finished and the entire
     * addition of the imageview to linear layout is finished.
     *
     * @param card The card to add to the specified <code>board</code>
     * @param board The board to add the <code>card</code> in
     * @param frontFacing Whether the <code>card</code> is front facing or back facing
     * @param onEnd Listener that runs when given <code>card</code> is add to the <code>board</code>
     */
    private void addCardImageViewToBoard(Card card, LinearLayout board, boolean frontFacing, YoYo.AnimatorCallback onEnd){
        int width = getResources().getDimensionPixelSize(R.dimen.card_width);
        int height = getResources().getDimensionPixelSize(R.dimen.card_height);
        int sideMargin = getResources().getDimensionPixelSize(R.dimen.card_side_margins);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;
        params.setMarginStart(sideMargin);
        params.setMarginEnd(sideMargin);
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setLayoutParams(params);
        imageView.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.material_white));
        imageView.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, R.drawable.back));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(12);
        }
        board.addView(imageView);
        showCard(card, imageView, frontFacing, onEnd);
    }

    /**
     * Adds the card as a child to the given linear layout by creating an imageview and
     * setting the appropriate params. <code>frontFacing</code> determines if a cards imageview
     * shows the back of the card of the front side of the card
     *
     * @param card The card to add to the specified <code>board</code>
     * @param board The board to add the <code>card</code> in
     * @param frontFacing Whether the <code>card</code> is front facing or back facing
     */
    private void addCardImageViewToBoard(Card card, LinearLayout board, boolean frontFacing){
        int width = getResources().getDimensionPixelSize(R.dimen.card_width);
        int height = getResources().getDimensionPixelSize(R.dimen.card_height);
        int sideMargin = getResources().getDimensionPixelSize(R.dimen.card_side_margins);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;
        params.setMarginStart(sideMargin);
        params.setMarginEnd(sideMargin);
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setLayoutParams(params);
        imageView.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.material_white));
        imageView.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, R.drawable.back));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(12);
        }
        board.addView(imageView);
        showCard(card, imageView, frontFacing);
    }

//    private void promptForRestart(int delayMillis){
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new MaterialDialog.Builder(MainActivity.this)
//                        .title("Game Over")
//                        .content("Would you like to restart?")
//                        .positiveText("Yes")
//                        .negativeText("No")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                MainActivity.this.recreate();
//                                return;
//                            }
//                        })
//                        .onNegative(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//        }, delayMillis);
//    }

    /**
     * Clears the button listeners and disables the buttons
     */
    private void clearListeners(){
        hitBtn.setEnabled(false);
        standBtn.setEnabled(false);
        hitBtn.setOnClickListener(null);
        standBtn.setOnClickListener(null);
    }

    /**
     * Sets the textview in the board that player won
     */
    private void playerWon(){
        gameStatusText.setText("PLAYER WON");
    }

    /**
     * Sets the textview in the board that dealer won
     */
    private void dealerWon(){
        gameStatusText.setText("DEALER WON");
    }

    /**
     * Sets the textview in the board that its a tie
     */
    private void itsATie(){
        gameStatusText.setText("IT'S A TIE");
    }

    /**
     * Checks if the dealer or the player won and runs the appropriate method: <code>playerWon()</code>,
     * <code>dealerWon()</code>, <code>itsATie()</code> and clears the listeners
     */
    private void checkWhoWon(){
        if (dealer.getHandTotal() > 21 && player.getHandTotal() <= 21){ //dealer busted & player didnt
            playerWon();
        }
        else if (dealer.getHandTotal() > 21 && player.getHandTotal() > 21) { //dealer busted & player busted
            dealerWon();
        }
        else if (dealer.getHandTotal() < 21 && player.getHandTotal() < 21){ //both hands are less than 21
            if (dealer.getHandTotal() < player.getHandTotal()){ //dealer hand is lower
                playerWon();
            }
            else if (dealer.getHandTotal() > player.getHandTotal()){ //dealer hand is higher
                dealerWon();
            }
            else { // tie
                itsATie();
            }
        }
        else if (dealer.getHandTotal() < 21 && player.getHandTotal() > 21) { // dealer hand is lower than 21 and player hand higher than 21
            dealerWon();
        }
        else if (dealer.getHandTotal() == 21 && player.getHandTotal() != 21){ // dealer hand is 21 and player hand isnt
            dealerWon();
        }
        else if (dealer.getHandTotal() != 21 && player.getHandTotal() == 21){ // dealer hand isnt 21 and player hand is
            playerWon();
        } else if (dealer.getHandTotal() == 21 && player.getHandTotal() == 21) { //both have 21
            if (dealer.hasBlackJack())
                dealerWon();
            else if (player.hasBlackJack())
                playerWon();
            else
                itsATie();
        }
        clearListeners();
        //promptForRestart(3000);
    }

    /**
     * Shows the hidden second card of the dealer and then plays the dealer
     */
    private void showDealerCardAndPlayDealer(){
        ImageView imageView = (ImageView) dealerBoardLinearLayout.getChildAt(1);
        showCard(dealer.getCard(1), imageView, true, new YoYo.AnimatorCallback() {
            @Override
            public void call(Animator animator) {
                dealerTextView.setText("Dealer Hand: " + dealer.getHandTotal());
                playDealer();
            }
        });
    }

    /**
     * Initializes the listeners of the UI components
     */
    private void initListeners(){
        if (player.getHandTotal() < 21){ //if players hand is totaling less than 21 hit is enabled
            hitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Card cardDrawn = player.draw(dealer.deal(), new Player.PlayerListener() {
                        @Override
                        public void onDraw(int handTotal) {
                            playerTextView.setText("Player Hand: " + handTotal);
                        }
                    });
                    addCardImageViewToBoard(cardDrawn, playerBoardLinearLayout, true);
                    playerTimesHit++;
                    if (player.getHandTotal() >= 21 || playerTimesHit >= MAX_HITS){ //player is done playing and its dealers turn
                        showDealerCardAndPlayDealer();
                    }
                }
            });
            standBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDealerCardAndPlayDealer(); //player is done playing and its dealers turn
                }
            });
        } else {
            //hand is more than 21 or 21 and we need to check who wins
            checkWhoWon();
        }
    }

    /**
     * Dealer starts playing and it decides whether to draw or not. Otherwise the check for who won is run
     */
    private void playDealer(){
        if (dealerShouldDraw()){
            Card cardDrawn = dealer.draw();
            addCardImageViewToBoard(cardDrawn, dealerBoardLinearLayout, true, new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {
                    dealerTimesHit++;
                    dealerTextView.setText("Dealer Hand: " + dealer.getHandTotal());
                    playDealer();
                }
            });
        } else {
            checkWhoWon();
        }
    }

    /**
     * Decides whether the dealer should draw a card based on the conditions that
     * the players hand is not more than 21,
     * the dealer has hit less than <code>MAX_HIT</code>,
     * the dealers hand is not more than 21,
     * the dealers hand is less than the players hand,
     * and if
     * the dealers hand is equal to the players hand it draws if the difference of hand total and 21 is not more than 3
     *
     * @return
     */
    private boolean dealerShouldDraw(){
        if (dealerTimesHit > MAX_HITS)
            return false;

        if (player.getHandTotal() > 21)
            return false;
        else if (dealer.getHandTotal() > 21)
            return false;

        if (dealer.getHandTotal() < player.getHandTotal()) {
            return true;
        }
        else if (dealer.getHandTotal() == player.getHandTotal()){
            int diff = 21 - dealer.getHandTotal();
            if (diff >= 3)
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Helper method to set the text of the dealer hand status and an X showing the hidden card
     */
    private void setDealerText(){
        dealerTextView.setText("Dealer Hand: " + dealer.getCard(0).getValue() + " + X");
    }

    /**
     * Shows the cards of the player by animating them in
     * Shows the dealers cards by animating them in. One of them animates in hidden
     * Sets the dealer status text by dealers hand total
     */
    private void setupRound(){
        showPlayerCards();
        showDealerCards();
        setDealerText();
    }

    /**
     * Shows the first 2 players cards after a 2 second delay by animating them in and when its done initializes
     * the listeners on the app UI components after a 3 second delay.
     */
    private void showPlayerCards(){

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(player.getCard(0), playerBoardLinearLayout, true);
            }
        },2000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(player.getCard(1), playerBoardLinearLayout, true, new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        playerTextView.setText("Player Hand: " + player.getHandTotal());
                        initListeners();
                    }
                });
            }
        },3000);
    }

    /**
     * Shows the first 2 cards of the dealer with a 1 second delay where one is back facing by animating them in.
     */
    private void showDealerCards(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(dealer.getCard(0), dealerBoardLinearLayout, true);
            }
        },1000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(dealer.getCard(1), dealerBoardLinearLayout, false);
            }
        },1000);
    }

    /**
     * Helper method that animates the showing effect of the imageview that represents the card
     * @param card The card object that we want to show by <code>where</code> imageview
     * @param where The imageview that represents the <code>card</code> object
     * @param frontFacing Whether the card animates front facing or back facing
     */
    private void showCard(final Card card, final ImageView where, final boolean frontFacing){

        YoYo.with(Techniques.FlipInX).duration(500).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (frontFacing)
                    where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, card.getDrawableId()));
                else
                    where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, R.drawable.back));
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).playOn(where);
    }

    /**
     * Helper method that animates the showing effect of the imageview that represents the card.
     * Notifies the user of the end of the animation by the listener <code>onEnd</code>
     *
     * @param card The card object that we want to show by <code>where</code> imageview
     * @param where The imageview that represents the <code>card</code> object
     * @param frontFacing Whether the card animates front facing or back facing
     * @param onEnd Listener that notifies the caller for the end of the animation
     */
    private void showCard(final Card card, final ImageView where, final boolean frontFacing, YoYo.AnimatorCallback onEnd){
        YoYo.with(Techniques.FlipInX).duration(500).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (frontFacing)
                    where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, card.getDrawableId()));
                else
                    where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, R.drawable.back));
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).onEnd(onEnd).playOn(where);
    }








}
