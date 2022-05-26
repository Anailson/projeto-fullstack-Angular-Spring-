package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;


/*
 * Criando toda a regra de negocio pra que não seja salvo dados sem informar lançamentos
 */
@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoRepository;
	
	
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		
		Optional<Pessoa> pessoa = pessoRepository.findById(lancamento.getPessoa().getCodigo());//ser a pessoa tiver inativa lança msg
		
		if(pessoa == null || !pessoa.isPresent()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
