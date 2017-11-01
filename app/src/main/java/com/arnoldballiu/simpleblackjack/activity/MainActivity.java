package com.arnoldballiu.simpleblackjack.activity;

import android.animation.Animator;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

//    @BindView(R.id.dealer_card_1_imageView)
//    ImageView dCard1ImageView;
//    @BindView(R.id.dealer_card_2_imageView)
//    ImageView dCard2ImageView;
//    @BindView(R.id.player_card_1_imageView)
//    ImageView pCard1ImageView;
//    @BindView(R.id.player_card_2_imageView)
//    ImageView pCard2ImageView;
    @BindView(R.id.hit_btn)
    Button hitBtn;
    @BindView(R.id.stand_btn)
    Button standBtn;
    @BindView(R.id.dealer_hand_board)
    LinearLayout dealerBoardLinearLayout;
    @BindView(R.id.player_hand_board)
    LinearLayout playerBoardLinearLayout;

    Dealer dealer = Dealer.init();
    Player player = Player.init(dealer.deal(), dealer.deal());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        showDealerCard();
        showPlayerCards();

    }

    private void addCardImageViewToBoard(Card card, LinearLayout board, YoYo.AnimatorCallback onEnd){
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
        showCard(card, imageView, onEnd);
    }

    private void addCardImageViewToBoard(Card card, LinearLayout board){
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
        showCard(card, imageView);
    }

    private void initListeners(){
        hitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card cardDrawn = player.draw(dealer.deal());
                addCardImageViewToBoard(cardDrawn, playerBoardLinearLayout);
            }
        });
    }

    private void showPlayerCards(){

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(player.getCard(0), playerBoardLinearLayout);
            }
        },2000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(player.getCard(1), playerBoardLinearLayout, new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        initListeners();
                    }
                });
            }
        },3000);
    }

    private void showDealerCard(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                addCardImageViewToBoard(dealer.getCard(0), dealerBoardLinearLayout);
            }
        },1000);
    }

    private void showCard(final Card card, final ImageView where){

        YoYo.with(Techniques.FlipInX).duration(500).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, card.drawableId));
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

    private void showCard(final Card card, final ImageView where, YoYo.AnimatorCallback onEnd){
        YoYo.with(Techniques.FlipInX).duration(500).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                where.setImageDrawable(SBJUtils.getDrawable(MainActivity.this, card.drawableId));
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
