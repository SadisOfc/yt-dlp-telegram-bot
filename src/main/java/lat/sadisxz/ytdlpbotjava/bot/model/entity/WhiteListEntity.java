package lat.sadisxz.ytdlpbotjava.bot.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "whitelist")
public class WhiteListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String name;
    @Column(nullable = false)
    private Boolean status;
    @Column(nullable = false)
    private LocalDateTime updated_at;

    public WhiteListEntity(){}
    public WhiteListEntity(String name, Boolean status) {
        this.status = status;
        this.name = name;
    }

    @PrePersist
    @PreUpdate
    public void setTime(){
        this.updated_at = LocalDateTime.now();
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

}
