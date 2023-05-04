package com.clinic.repository.dictionary;

import com.clinic.entity.dictionary.Doctor;
import com.clinic.repository.ReadOnlyRepository;

import java.util.List;

public interface DoctorsRepository extends ReadOnlyRepository<Doctor,String> {
    List<Doctor> findAllBySpecIdOrderByName(String specs);
}
