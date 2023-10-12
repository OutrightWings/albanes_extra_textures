package com.outrightwings.albanes_extra_textures;

import sekelsta.horse_colors.entity.genetics.EquineGenome;
import sekelsta.horse_colors.entity.genetics.HorsePatternCalculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SplashFactorAccessor {
    private static Method splashFactor;

    public static void LoadMethod() {
        try {
            splashFactor = HorsePatternCalculator.class.getDeclaredMethod("getSplashFactor", EquineGenome.class);
            splashFactor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getSplashFactor(EquineGenome horse){
        try {
            return (int) splashFactor.invoke(1,horse);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
