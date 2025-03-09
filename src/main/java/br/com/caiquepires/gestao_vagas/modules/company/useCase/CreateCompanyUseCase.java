package br.com.caiquepires.gestao_vagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caiquepires.gestao_vagas.exeptions.UserFoundExeption;
import br.com.caiquepires.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.caiquepires.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundExeption();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

       return this.companyRepository.save(companyEntity);

    }
}
