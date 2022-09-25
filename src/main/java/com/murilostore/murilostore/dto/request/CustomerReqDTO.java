package com.murilostore.murilostore.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDTO {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18L, message = "Age must be greater than 18")
    private int age;

    @CPF(message = "CPF is invalid")
    private String cpf;

    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email is required")
    private String email;

}
