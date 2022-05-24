package br.com.treinaweb.javajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type;
    private Date expiresAt;


}
