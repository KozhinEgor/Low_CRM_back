package com.clinic.repository;

import com.clinic.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository  extends JpaRepository<Clients, Long> {
}
