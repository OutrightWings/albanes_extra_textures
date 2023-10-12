package com.outrightwings.albanes_extra_textures.mixin;

import com.outrightwings.albanes_extra_textures.Config;
import com.outrightwings.albanes_extra_textures.SplashFactorAccessor;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sekelsta.horse_colors.client.renderer.TextureLayer;
import sekelsta.horse_colors.entity.genetics.EquineGenome;
import sekelsta.horse_colors.entity.genetics.HorseColorCalculator;
import sekelsta.horse_colors.entity.genetics.HorsePatternCalculator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Mixin(HorsePatternCalculator.class)
public class HorsePatternMixin {

    @Inject(method = "addFaceMarkings(Lsekelsta/horse_colors/entity/genetics/EquineGenome;Ljava/util/List;)V", at = @At(value= "HEAD"),remap = false, cancellable = true)
    private static void faceTexture(EquineGenome horse, List<TextureLayer> textureLayers, CallbackInfo ci){
        int splash = SplashFactorAccessor.getSplashFactor(horse);
        int white = 2 * splash + horse.getSabinoFactor();
        int random = horse.getRandom("face_white") >>> 1;
        white += random & 3;

        if(white > 0) {
            String marking = "";
            if(!Config.random_textures){
                //This number assumes a range between [0-125], as that is the range of white (ignoring way high upper values) in the base code
                //It then converts it down into my range of values.
                white = (int)((Config.face_textures_range/125f) * white);
                white = Math.max(1,white);
                //Let marking map onto array. Saving last few for splash only expression with white
                if(splash >= 5){
                    marking = Config.face_textures.get(Math.min(Config.face_textures_range,white)-1);
                }else{
                    marking = Config.face_textures.get(Math.min(Config.face_textures_non_splash_range,white)-1);
                }
            } else{
                marking = Config.face_textures.get(random % Config.face_textures_range);
            }

            if(Config.debug_mode){
                System.out.printf("Splash: %d, White: %d, Marking: %s, Random: %d",splash,white,marking,random);
            }

            TextureLayer layer = new TextureLayer();
            layer.name = HorseColorCalculator.fixPath("face/" + marking);
            textureLayers.add(layer);
        }
        ci.cancel();
    }
}
