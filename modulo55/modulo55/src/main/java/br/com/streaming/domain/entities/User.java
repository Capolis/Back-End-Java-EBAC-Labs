package br.com.streaming.domain.entities;

import br.com.streaming.domain.enums.PlanType;
import br.com.streaming.domain.enums.ProfileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String password;

    // Autorrelacionamento: Aponta para o usuário assinante (titular da conta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "profile_type", nullable = false, length = 20)
    private ProfileType profileType;

    @Column(name = "is_subscriber", nullable = false)
    private Boolean isSubscriber;

    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "plan_type", length = 20)
    private PlanType planType;

    @Column(name = "allows_adult_content", columnDefinition = "boolean default false")
    private Boolean allowsAdultContent;

    // Contrutor

    public User() {
    }

    // Setters e Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public Boolean getIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(Boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public Boolean getAllowsAdultContent() {
        return allowsAdultContent;
    }

    public void setAllowsAdultContent(Boolean allowsAdultContent) {
        this.allowsAdultContent = allowsAdultContent;
    }


}