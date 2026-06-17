package br.com.ebac.utils;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

// Componente utilitário isolado para gerenciar o estado das sessões em memória
@Component
public class ActiveUsersUtils {
    private final ConcurrentHashMap<String, LocalDateTime> activeSessions = new ConcurrentHashMap<>();

    public void createSession(String token) {
        // Define o tempo limite para 1 minuto a partir de agora
        activeSessions.put(token, LocalDateTime.now().plusMinutes(1));
    }

    public boolean isSessionValid(String token) {
        if (token == null || !activeSessions.containsKey(token)) {
            return false;
        }
        
        LocalDateTime expirationTime = activeSessions.get(token);
        if (LocalDateTime.now().isAfter(expirationTime)) {
            activeSessions.remove(token);
            return false;
        }
        return true;
    }
}