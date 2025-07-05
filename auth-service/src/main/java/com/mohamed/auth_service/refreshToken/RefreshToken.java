package com.mohamed.auth_service.refreshToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("refresh_token")
public class RefreshToken {
    @Id
    private UUID id;

    private String token;

    @Column("user_id")
    private UUID userId;

    @Column("expiry_date")
    private Date expiryDate;
}
