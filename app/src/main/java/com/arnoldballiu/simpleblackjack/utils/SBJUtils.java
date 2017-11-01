package com.arnoldballiu.simpleblackjack.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.arnoldballiu.simpleblackjack.R;
import com.arnoldballiu.simpleblackjack.object.Card;

/**
 * Created by arnb on 11/1/17.
 */

public class SBJUtils {

    public static Drawable getDrawable(Context c, int id){
        return ContextCompat.getDrawable(c, id);
    }

    public static int toDp(Context c, int pixel){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, c.getResources().getDisplayMetrics());
    }

}
