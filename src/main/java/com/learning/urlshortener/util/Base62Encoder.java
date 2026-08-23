package com.learning.urlshortener.util;

// Converts a database ID (like 12345) into a short, URL-safe string (like "3d7").
// This is THE classic URL shortener interview topic — worth understanding fully.
//
// Why Base62? It uses all digits + uppercase + lowercase letters (0-9, a-z, A-Z)
// = 62 characters. More characters per "digit" than decimal (base 10) means
// the same number needs FEWER characters to represent — that's what makes the
// code short. It's also 100% URL-safe (no +, /, or = like Base64 has).
public class Base62Encoder {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length(); // 62

    public static String encode(long id) {
        if (id == 0) return String.valueOf(ALPHABET.charAt(0));

        StringBuilder result = new StringBuilder();
        while (id > 0) {
            int remainder = (int) (id % BASE);
            result.append(ALPHABET.charAt(remainder));
            id /= BASE;
        }
        // We built the string backwards (least significant digit first), so reverse it.
        return result.reverse().toString();
    }
}
