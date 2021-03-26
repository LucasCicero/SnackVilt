package com.snackapp.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Semana implements Serializable {
	
	@Id
	private long codigo;
	
	@Column(name="nome", nullable=false, unique=true)
	private String nomeSemana;
	
	@ManyToOne
	private Pessoa pessoa;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNomeSemana() {
		return nomeSemana;
	}

	public void setNomeSemana(String nomeSemana) {
		this.nomeSemana = nomeSemana;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}
