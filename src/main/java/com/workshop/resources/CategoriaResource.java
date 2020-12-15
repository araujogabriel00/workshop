package com.workshop.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workshop.entitites.Categoria;
import com.workshop.services.CategoriaServ;

///CATEGORIA PRINCIPAL
///DEVE CONTER AS ANOTAÇÕES RESTCONTROLLER E REQUESTMAPPING
///A FUNÇÃO RESPONSAVEL PELA BUSCA DE CONTER A ANOTAÇÃO REQUESTMAPPING
@RestController
@RequestMapping(value = "/categorias") // O VALUE DEFINE COMO SERÁ CHAMADO O RESOURCE NA URL
public class CategoriaResource {

	/// RESOURSCES DEVEM TER A CLASSE DE SERVIÇO DE SUA CORRESPONDENTE ENTIDADE
	@Autowired
	private CategoriaServ categoriaserv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = categoriaserv.find(id);
		return ResponseEntity.ok().body(obj);

	}

	// FAZER A NOVA CATEGORIA VIR COM UM NOVO ID
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Categoria categoria) {
		categoria = categoriaserv.insert(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {

		categoria.setId(id);
		categoria = categoriaserv.update(categoria);

		return ResponseEntity.noContent().build();
	}

}
