package com.outrightwings.albanes_extra_textures;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> TEXTURE_LIST = BUILDER
            .comment("A list of the names of all possible textures in order from least to most white")
            .defineList("textures",
                    List.of(
                        "etoile",
                        "star",
                        "marry",
                        "lightning",
                        "texture",
                        "star2",
                        "light",
                        "usky",
                        "opium",
                        "amour",
                        "strip",
                        "aisha",
                        "last",
                        "basic",
                        "moon",
                        "leri",
                        "utopie",
                        "azer",
                        "excellent",
                        "aslan",
                        "hope",
                        "touareg",
                        "alas",
                        "chimie",
                        "hert",
                        "defaut",
                        "club",
                        "tuto",
                        "line",
                        "doublure",
                        "love",
                        "zylu",
                        "roty",
                        "ella",
                        "osiris",
                        "inou",
                        "axiome",
                        "terre",
                        "koko",
                        "blaze",
                        "sunshine",
                        "molly",
                        "nevada",
                        "heart",
                        "kill",
                        "kepresh",
                        "ziren",
                        "eclair",
                        "kyju",
                        "coup",
                        "zouav",
                        "unic",
                        "under",
                        "aoa",
                        "umour",
                        "uzes",
                        "yolo",
                        "kissing",
                        "entree",
                        "moonlight",
                        "joyce",
                        "happy",
                        "infini",
                        "donner",
                        "broad_blaze",
                        "pomme",
                        "billy",
                        "aloha",
                        "alo"),
                    name -> name instanceof String);
    private static final ForgeConfigSpec.IntValue MAX_TEXTURES = BUILDER
            .comment("Total Amount of textures defined above")
            .defineInRange("max_textures", 69, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue MAX_NON_SPLASH = BUILDER
            .comment("Subset in range above that is reserved for non splash")
            .defineInRange("max_nonSplash", 63, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.BooleanValue RANDOM_MODE = BUILDER
            .comment("Should horses get a texture randomly, instead of based off their white value?")
            .define("random_mode", false);
    private static final ForgeConfigSpec.BooleanValue DEBUG_MODE = BUILDER
            .comment("Enable debug mode. This spits out horse data into the log")
            .define("debug_mode", false);
    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static List<String> face_textures;
    public static int face_textures_range;
    public static int face_textures_non_splash_range;
    public static boolean random_textures;
    public static boolean debug_mode;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        face_textures = TEXTURE_LIST.get().stream().map(String::valueOf).collect(Collectors.toList());
        face_textures_range = MAX_TEXTURES.get();
        face_textures_non_splash_range = MAX_NON_SPLASH.get();
        debug_mode = DEBUG_MODE.get();
        random_textures = RANDOM_MODE.get();
    }
}
