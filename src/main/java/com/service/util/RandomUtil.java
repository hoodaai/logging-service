package com.service.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 10;

    private RandomUtil() {
    }

    /**
     * Generates a secret key.
     *
     * @return the generated secret key
     */
    public static String generateSecretKey() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an application id.
     *
     * @return the generated application id
     */
    public static String generateApplicationId() {
        return RandomStringUtils.randomAlphabetic(DEF_COUNT);
    }

}
