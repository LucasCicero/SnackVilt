package com.snackapp.models;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	
	@Column(name="nome", nullable=false, unique=true)
	private String pessoaNome;
	
	@OneToMany
	private List<Semana> semanas;


	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}

	public List<Semana> getSemanas() {
		return semanas;
	}

	public void setSemanas(List<Semana> semanas) {
		this.semanas = semanas;
	}

	
	}

	
	
	


