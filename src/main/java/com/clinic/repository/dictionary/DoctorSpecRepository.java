package com.clinic.repository.dictionary;

import com.clinic.entity.dictionary.DoctorSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorSpecRepository extends JpaRepository<DoctorSpec,String> {
}
