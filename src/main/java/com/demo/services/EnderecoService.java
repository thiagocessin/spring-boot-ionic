package com.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Endereco;
import com.demo.repositories.EnderecoRepository;
import com.demo.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public List<Endereco> findAll() {
		return repo.findAll();
	}
	
	
	public Endereco save(Endereco categoria) {
		return repo.save(categoria);
	}

}
