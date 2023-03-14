package by.kashtan.supplies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_user")
@NoArgsConstructor
public class User {
    public User(String accountName, String jobTitle, String department, Boolean isActive, String application) {
        this.accountName = accountName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.isActive = isActive;
        this.application = application;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;

    private String jobTitle;

    private String department;

    private Boolean isActive;

    private String application;
}
