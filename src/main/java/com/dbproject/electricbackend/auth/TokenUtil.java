package com.dbproject.electricbackend.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dbproject.electricbackend.exception.CustomException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public static String createToken(int userId, String userPassword) {
        String token = JWT.create().withAudience(String.valueOf(userId)).withAudience(new Date().toString())
                .sign(Algorithm.HMAC256(userPassword));
        pool.register(token, userId);
        return token;
    }

    public static boolean verifyToken(String token) {
        return pool.check(token);
    }

    public static int verifyTokenAndGetUserId(String token) throws CustomException {
        return pool.checkAndGet(token);
    }

    public static void cancelToken(String token) {
        pool.remove(token);
    }

    private static class TokenPool {
        // Singleton Mode
        private static final int EXPIRE_INTERVAL = 30;  // minutes

        private final Map<String, Date> expirationMap = new HashMap<>();
        private final Map<String, Integer> tokenToUserMap = new HashMap<>();

        private TokenPool() {}

        // register new or refresh existed
        public void register(String token, int userId) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, EXPIRE_INTERVAL);
            Date expire = cal.getTime();
            expirationMap.put(token, expire);
            tokenToUserMap.put(token, userId);
        }

        public void remove(String token) {
            tokenToUserMap.remove(token);
            expirationMap.remove(token);
        }

        public boolean check(String token) {
            if (expirationMap.containsKey(token)) {
                Date expire = expirationMap.get(token);
                if ((new Date()).after(expire)) {
                    // expired!
                    remove(token);
                    return false;
                }
                // refresh
                register(token, tokenToUserMap.get(token));
                return true;
            }
            return false;
        }

        public int checkAndGet(String token) throws CustomException {
            if (!check(token)) {
                throw CustomException.defined(CustomException.Define.INVALID_TOKEN);
            }
            assert tokenToUserMap.containsKey(token);
            return tokenToUserMap.get(token);   // assert not null
        }
    }

    private static final TokenPool pool = new TokenPool();
}
