package Core.Resources;

import java.util.Random;

public final class Constants {
    public static final String ALPHANUMERIC = "abcdefghijklmnñopqrstuvwxyzáéíóúäëïöüÿABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÄËÏÖÜ 1234567890";
    public static final String SPECIAL_CHARACTERS = "|!\"#$%&/()=¿?¡°'¨´*+~[]{}^`;:,.-_";
    public static final String ALL_CHARACTERS = ALPHANUMERIC + SPECIAL_CHARACTERS;
    public static final String EMAIL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~.";
    public static final String HTML_ATTRIBUTE_STYLE = "style";
    public static final String HTML_ATTRIBUTE_VALUE = "value";
    public static final String NAMES = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    public static final String STYLE_OF_WAIT_MODAL = "display:none;";
    public static final String TEST_CONFIG_PARAMS = "ConfigXML";
    public static final Random SEED_RANDOM = new Random();
    
    private Constants() {
        throw new AssertionError();
    }
}
