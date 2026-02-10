package lat.sadisxz.ytdlpbotjava.bot.model.entity;

import jakarta.persistence.*;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "username", unique = true, length = 100)
    private String username;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserEntity(){}
    public UserEntity(Long id, String name, String username, UserRole role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(username, that.username) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, role);
    }
}
