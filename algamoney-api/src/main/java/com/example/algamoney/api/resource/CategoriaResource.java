package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@GetMapping
	public  List<Categoria> listar(){
		
		return categoriaRepository.findAll();
	
	}
	
	/*----------------------SALVAR NOVOS REGISTROS----------------------------*/
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED) //STATUS 201 CREATED
	public ResponseEntity<Categoria> criar (@RequestBody Categoria categoria, HttpServletResponse response) {
		
		Categoria categoriaSalvar = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(categoriaSalvar.getCodigo())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
	 return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalvar);
		
	}
	/*BUSCANDO PELO CODIGO*////.metadata/
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
	Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
	return categoria.isPresent() ? 
	        ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
	
	
}
