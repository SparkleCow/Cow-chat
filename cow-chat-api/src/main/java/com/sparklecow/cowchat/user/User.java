package com.sparklecow.cowchat.user;

import com.sparklecow.cowchat.chat.Chat;
import com.sparklecow.cowchat.common.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseAuditing implements UserDetails {

    private static final int LAST_ACTIVATE_INTERVAL = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    private String password;

    private String email;

    private String imagePath;

    private LocalDateTime lastSeen;

    @OneToMany
    private List<Chat> chatsAsSender;

    @OneToMany
    private List<Chat> chatsAsRecipient;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Transient
    public boolean isUserOnline(){
        return lastSeen != null && !lastSeen.isBefore(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

}
