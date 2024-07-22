package com.home.paylod;

import lombok.Data;

@Data
public class JwtResponse {
    private String type="Bearer";
    private String token;
}
