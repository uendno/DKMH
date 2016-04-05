package com.thangtv.dkmh.ultils;

/**
 * Created by uendno on 05/04/2016.
 */
public class Color {
    public static int alterColor(int color, float factor) {
        int a = (color & (0xFF << 24)) >> 24;
        int r = (int) (((color & (0xFF << 16)) >> 16) * factor);
        int g = (int) (((color & (0xFF << 8)) >> 8) * factor);
        int b = (int) ((color & 0xFF) * factor);
        return android.graphics.Color.argb(a,r,g,b);
    }
}
