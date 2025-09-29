package com.du.valid1.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 비밀번호 해싱
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // 비밀번호 비교 (로그인 시 사용할 수 있음)
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
