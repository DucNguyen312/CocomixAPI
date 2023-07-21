package com.example.library.Service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CacheService {
    private final Cache emailTokenCache;

    public CacheService(CacheManager cacheManager) {
        this.emailTokenCache = cacheManager.getCache("emailTokenCache");
    }

    public String generateAndCacheToken(String email) {
        String token = generateToken();
        emailTokenCache.put(token, email);
        return token;
    }

    public String getEmailFromToken(String token) {
        return (String) emailTokenCache.get(token).get();
    }

    public void removeToken(String token) {
        emailTokenCache.evict(token);
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
