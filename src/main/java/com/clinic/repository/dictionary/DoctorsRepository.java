package com.clinic.repository.dictionary;

import com.clinic.entity.dictionary.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctor,String> {
    List<Doctor> findAllBySpecIn(List<String> specs);
}
