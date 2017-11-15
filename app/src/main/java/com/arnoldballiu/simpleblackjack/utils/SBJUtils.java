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

    /**
     * Helper method to wrap up the calling of ContextCompat.
     * Really useless.
     *
     * @param c Context or Activity
     * @param id Drawable id to obtain
     * @return A drawable object specified by <code>id</code>
     */
    public static Drawable getDrawable(Context c, int id){
        return ContextCompat.getDrawable(c, id);
    }
}
