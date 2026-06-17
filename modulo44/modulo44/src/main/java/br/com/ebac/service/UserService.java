package br.com.ebac.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ebac.model.User;
import br.com.ebac.utils.ActiveUsersUtils;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    
    private final UserRepository userRepository;
    private final ActiveUsersUtils sessionUtils;

    @Autowired
    public UserService(UserRepository userRepository, ActiveUsersUtils sessionUtils) {
        this.userRepository = userRepository;
        this.sessionUtils = sessionUtils;
    }

    public void registerUser(User user) {
        logger.info("Inciando registro para o usuário: {}", user.getUsername());
        userRepository.save(user);
        logger.info("Usuário registrado com sucesso no banco de dados MySQL.");
    }

    public String login(String username, String password) {
        logger.info("Tentando fazer login para o usuário: {}", username);
        
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            String sessionToken = UUID.randomUUID().toString();
            sessionUtils.createSession(sessionToken);
            logger.info("Login concluído com sucesso. Sessão temporária criada.");
            return sessionToken;
        }
        
        logger.warn("Login falhou para o usuário: {}", username);
        return null;
    }

    public boolean validateAccess(String token) {
        return sessionUtils.isSessionValid(token);
    }
}