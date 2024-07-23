package com.home.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(name = "forgot_password")
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpId", nullable = false)
    private Integer fpId;



    @Column(name = "otp", nullable = false)
    private Integer otp;

    @Column(name = "expiration_time", nullable = false)
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}