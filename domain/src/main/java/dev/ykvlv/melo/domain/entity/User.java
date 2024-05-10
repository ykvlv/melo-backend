package dev.ykvlv.melo.domain.entity;

import dev.ykvlv.melo.domain.type.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "melo_user")
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "melo_user_id_seq")
    @SequenceGenerator(sequenceName = "melo_user_id_seq", name = "melo_user_id_seq", allocationSize = 1)
    private Long id;

    @NonNull
    @Column(name = "username", nullable = false)
    private String username;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "registered_at", nullable = false)
    private LocalDate registeredAt;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(getRole());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
