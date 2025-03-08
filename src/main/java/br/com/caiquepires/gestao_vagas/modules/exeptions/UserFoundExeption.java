package br.com.caiquepires.gestao_vagas.modules.exeptions;

public class UserFoundExeption  extends RuntimeException {
    public UserFoundExeption(){
        super("Usuário já existe.");
    }
}
