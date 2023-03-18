package com.eh.gc.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 17 MAR, 2023
 * @version 1.1
*/
@Getter @Setter
public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
