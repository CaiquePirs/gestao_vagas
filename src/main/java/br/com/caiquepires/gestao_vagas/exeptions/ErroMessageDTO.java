package br.com.caiquepires.gestao_vagas.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroMessageDTO {
    private String message; 
    private String field; 

}
