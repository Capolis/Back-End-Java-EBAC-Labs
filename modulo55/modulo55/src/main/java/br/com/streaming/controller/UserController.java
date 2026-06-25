package br.com.streaming.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.streaming.domain.dto.SubUserCreateDTO;
import br.com.streaming.domain.dto.UserCreateDTO;
import br.com.streaming.domain.dto.UserResponseDTO;
import br.com.streaming.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/subscriber")
    public ResponseEntity<UserResponseDTO> createSubscriber(@RequestBody UserCreateDTO dto) {
        UserResponseDTO response = userService.createSubscriber(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{subscriberId}/dependent")
    public ResponseEntity<UserResponseDTO> createDependent(
            @PathVariable Long subscriberId,
            @RequestBody SubUserCreateDTO dto) {

        UserResponseDTO response = userService.createDependent(subscriberId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{subscriberId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long subscriberId) {
        userService.deleteAccount(subscriberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{subscriberId}/plan")
    public ResponseEntity<String> migratePlan(
            @PathVariable Long subscriberId,
            @RequestBody br.com.streaming.domain.dto.PlanMigrationDTO request) {

        userService.migratePlan(subscriberId, request.newPlan());
        return ResponseEntity.ok("Plan migrated successfully to " + request.newPlan());
    }
}