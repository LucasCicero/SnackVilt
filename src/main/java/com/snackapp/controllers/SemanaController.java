package com.snackapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import com.snackapp.models.Pessoa;
import com.snackapp.models.Semana;
import com.snackapp.repository.PessoaRepository;
import com.snackapp.repository.SemanaRepository;


@Controller
public class SemanaController {

	@Autowired
	private SemanaRepository sr;
	@Autowired
	private PessoaRepository pr;

	@RequestMapping("/semana")
	public ModelAndView listaSemanas() {
		ModelAndView mv = new ModelAndView("listaSemanas");
		Iterable<Semana> semanas = sr.findAll();
		mv.addObject("semanas", semanas);
		return mv;
	}

	@RequestMapping("/pessoa")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("listaPessoas");
		Iterable<Pessoa> pessoas = pr.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesPessoa(@PathVariable("codigo") long codigo) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("semana/detalhesPessoa");
		mv.addObject("pessoa", pessoa);

		Iterable<Semana> semanas = sr.findByPessoa(pessoa);
		mv.addObject("semana", semanas);

		return mv;
	}

	@RequestMapping("/deletarPessoa")
	public String deletarPessoa(long codigo) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		pr.delete(pessoa);
		return "redirect:/pessoa";
	}

	@RequestMapping(value = "/cadastrarPessoa", method = RequestMethod.GET)
	public String form2() {
		return "semana/formPessoa";
	}

	@RequestMapping(value = "/cadastrarPessoa", method = RequestMethod.POST)
	public String form2(@Validated Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarPessoa";
		}

		pr.save(pessoa);
		attributes.addFlashAttribute("mensagem", "pessoa cadastrado com sucesso!");
		return "redirect:/cadastrarPessoa";
	}

	@RequestMapping(value = "/editarPessoa", method = RequestMethod.GET)
	public ModelAndView editarPessoa(long codigo) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("semana/editarPessoa");
		mv.addObject("pessoa", pessoa);
		return mv;
	}

	@RequestMapping(value = "/editarPessoa", method = RequestMethod.POST)
	public String updateSemana(@Validated Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		pr.save(pessoa);
		attributes.addFlashAttribute("successo", "Evento alterado com sucesso!");
		// return "redirect:/";

		return "redirect:/pessoa";
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesPessoaPost(@PathVariable("codigo") long codigo, @Validated Semana semana,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}

		Pessoa pessoa = pr.findByCodigo(codigo);
		// pessoa.setSemanas(semana);

		semana.setPessoa(pessoa);
		sr.save(semana);

		// pessoa.setSemanas(semana);

		attributes.addFlashAttribute("mensagem", "Semana adicionada com sucesso para o colaborador!");
		return "redirect:/{codigo}";

	}

	@RequestMapping("/deletarSemana")
	public String deletarSemana(String nomeSemana) {
		Semana semana = sr.findByNomeSemana(nomeSemana);
		sr.delete(semana);

		Pessoa pessoa = semana.getPessoa();
		long codigoLong = semana.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}

}
