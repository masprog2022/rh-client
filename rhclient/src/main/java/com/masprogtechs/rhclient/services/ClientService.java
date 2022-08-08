package com.masprogtechs.rhclient.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.masprogtechs.rhclient.dto.ClientDTO;
import com.masprogtechs.rhclient.entities.Client;
import com.masprogtechs.rhclient.exception.ResourceNotFoundException;
import com.masprogtechs.rhclient.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

		Page<Client> list = repository.findAll(pageRequest);

		return list.map(x -> new ClientDTO(x));

	}

	@Transactional
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		// Category entity = obj.get();
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Client does not exist!"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new ClientDTO(entity);
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
