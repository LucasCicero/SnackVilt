package com.snackapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.snackapp.models.Pessoa;
import com.snackapp.models.Semana;

public interface SemanaRepository extends CrudRepository<Semana, String> {

	Semana findByCodigo(long codigo);
	Iterable<Semana> findByPessoa(Pessoa pessoa);
	Semana findByNomeSemana(String nomeSemana);
	
	
}
