package br.com.caiquepires.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    @NotBlank()
    @Pattern(regexp = "^\\s+", message = "O campo [username] não pode conter espaços")
    private String username; 

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email; 

    @Length(min = 10, max = 100, message = "A [senha] deve conter de 10 a 100 caracteres")
    private String password; 

    private String site; 
    private String name; 
    private String desription;

    @CreationTimestamp
    private LocalDateTime createdAt; 

}
