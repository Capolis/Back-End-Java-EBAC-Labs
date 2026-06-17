package br.com.ebac.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.model.User;
import br.com.ebac.requests.UserRequest;
import br.com.ebac.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest request) {
        logger.info("Recebido pedido de registro de usuário.");
        
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        
        userService.registerUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRequest request) {
        logger.info("Recebido pedido de login.");
        String token = userService.login(request.getUsername(), request.getPassword());
        
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> accessProtectedResource(@RequestHeader(value = "Session-Token", required = false) String token) {
        logger.info("Acessando endpoint protegido.");
        
        if (userService.validateAccess(token)) {
            logger.info("Acesso concedido. Sessão é válida.");
            return ResponseEntity.ok("Acesso concedido! Você está dentro do limite de tempo.");
        }
        
        logger.warn("Acesso negado. Sessão ausente ou expirada.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você não está logado ou sua sessão expirou.");
    }
}