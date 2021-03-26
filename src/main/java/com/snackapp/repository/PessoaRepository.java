package com.snackapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.snackapp.models.Pessoa;
import com.snackapp.models.Semana;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{
	
	Iterable<Pessoa> findBySemanas(Semana semana);
	Pessoa findByPessoaNome(String pessoaNome);
	Pessoa findByCodigo(long codigo);
	

}
