package br.com.streaming.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.streaming.domain.dto.SubUserCreateDTO;
import br.com.streaming.domain.dto.UserCreateDTO;
import br.com.streaming.domain.dto.UserResponseDTO;
import br.com.streaming.domain.entities.User;
import br.com.streaming.domain.enums.PlanType;
import br.com.streaming.domain.enums.ProfileType;
import br.com.streaming.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependência pelo construtor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createSubscriber(UserCreateDTO dto) {
        // Regra de negócio: Assinante precisa ter mais de 18 anos
        if (dto.age() < 18) {
            throw new IllegalArgumentException("Subscribers must be at least 18 years old.");
        }

        User user = new User();
        user.setAge(dto.age());
        user.setProfileType(ProfileType.ADULT); // Maior de 18 é sempre ADULT
        user.setIsSubscriber(true);
        user.setPlanType(dto.planType());
        user.setAllowsAdultContent(Boolean.TRUE.equals(dto.allowsAdultContent()));
        
        // Criptografando a senha antes de salvar no banco!
        String hashedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        // Retorna o DTO sem a senha
        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getAge(),
            savedUser.getProfileType(),
            savedUser.getIsSubscriber(),
            savedUser.getPlanType()
        );
    }

    public UserResponseDTO createDependent(Long subscriberId, SubUserCreateDTO dto) {
        // Busca o assinante titular
        User subscriber = userRepository.findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("Subscriber not found."));

        if (!subscriber.getIsSubscriber()) {
            throw new IllegalArgumentException("The provided ID does not belong to a subscriber account.");
        }

        // Valida limite de dependentes do plano
        long currentDependents = userRepository.countBySubscriberId(subscriberId);
        int maxDependents = switch (subscriber.getPlanType()) {
            case BASIC -> 0;
            case STANDARD -> 2;
            case PREMIUM -> 4;
        };

        if (currentDependents >= maxDependents) {
            throw new IllegalStateException("Plan limit reached. Cannot add more dependents.");
        }

        // Define o Perfil baseado na idade
        ProfileType profile;
        if (dto.age() < 12) {
            profile = ProfileType.CHILD;
        } else if (dto.age() < 18) {
            profile = ProfileType.TEEN;
        } else {
            profile = ProfileType.ADULT;
        }

        // Monta o usuário dependente
        User dependent = new User();
        dependent.setAge(dto.age());
        dependent.setProfileType(profile);
        dependent.setIsSubscriber(false);
        dependent.setPlanType(subscriber.getPlanType()); // Herda o plano
        dependent.setAllowsAdultContent(false); // Por padrão, falso para dependentes
        dependent.setSubscriber(subscriber); // Cria o vínculo
        dependent.setPassword(passwordEncoder.encode(dto.password()));

        User savedUser = userRepository.save(dependent);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getAge(),
                savedUser.getProfileType(),
                savedUser.getIsSubscriber(),
                savedUser.getPlanType()
        );
    }

    public void deleteAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        
        if (!user.getIsSubscriber()) {
            throw new IllegalStateException("Only subscribers can delete accounts.");
        }
        
        // ON DELETE CASCADE no banco faz apagar o titular e os dependentes em cascata
        userRepository.delete(user);
    }

    // Método auxiliar para determinar o "peso" do plano e descobrir se é upgrade ou downgrade
    private int getPlanLevel(PlanType plan) {
        return switch (plan) {
            case BASIC -> 1;
            case STANDARD -> 2;
            case PREMIUM -> 3;
        };
    }

    public void migratePlan(Long subscriberId, PlanType newPlan) {
        User subscriber = userRepository.findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        if (!subscriber.getIsSubscriber()) {
            throw new IllegalStateException("Somente usuários pagantes podem atualizar o plano.");
        }

        if (subscriber.getPlanType() == newPlan) {
            throw new IllegalStateException("O usuário já possui este plano.");
        }

        boolean isDowngrade = getPlanLevel(newPlan) < getPlanLevel(subscriber.getPlanType());

        // Atualiza o plano do titular
        subscriber.setPlanType(newPlan);
        userRepository.save(subscriber);

        // Busca os dependentes vinculados
        java.util.List<User> dependents = userRepository.findBySubscriberId(subscriberId);

        if (isDowngrade) {
            // Regra: Em caso de downgrade, todos os dependentes são excluídos
            userRepository.deleteAll(dependents);
        } else {
            // Regra: Em caso de upgrade, os dependentes são mantidos e herdam o novo plano
            for (User dependent : dependents) {
                dependent.setPlanType(newPlan);
            }
            userRepository.saveAll(dependents);
        }
    }
}