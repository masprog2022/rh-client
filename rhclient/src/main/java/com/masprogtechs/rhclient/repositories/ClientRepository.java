package com.masprogtechs.rhclient.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masprogtechs.rhclient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
