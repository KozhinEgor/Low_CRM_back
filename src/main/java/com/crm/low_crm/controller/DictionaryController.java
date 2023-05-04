package com.crm.low_crm.controller;

import com.clinic.entity.dictionary.*;
import com.clinic.repository.dictionary.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dictionary")
@AllArgsConstructor
public class DictionaryController {
    private final DoctorsRepository doctorsRepository;
    private final DoctorSpecRepository doctorSpecRepository;
    private final ProductRepository productRepository;
    private final ProductSpecRepository productSpecRepository;
    private final UnitRepository unitRepository;

    @GetMapping("/doctor")
    public ResponseEntity<?> getDoctors(@RequestParam(required = false, name = "doctorSpecs") String specs){
        if (specs == null){
            return ResponseEntity.ok(doctorsRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                    .stream()
                    .map(Doctor::toDto)
                    .filter(x -> !x.getName().isBlank())
                    .collect(Collectors.toList()));
        }
        return ResponseEntity.ok(doctorsRepository.findAllBySpecIdOrderByName(specs)
                .stream()
                .map(Doctor::toDto)
                .filter(x -> !x.getName().isBlank()));
    }

    @GetMapping("/doctor-spec")
    public ResponseEntity<?> getDoctorSpecs(){
        return ResponseEntity.ok(doctorSpecRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(DoctorSpec::toDto).filter(x -> !x.getName().isBlank()));
    }
    @GetMapping("/unit")
    public ResponseEntity<?> getUnit(){
        return ResponseEntity.ok(unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(Unit::toDto)
                .filter(x -> !x.getName().isBlank()));
    }
    @GetMapping("/product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(Product::toDto)
                .filter(x -> !x.getName().isBlank()));
    }
    @GetMapping("/prouctSpec")
    public ResponseEntity<?> getProductSpec(){
        return ResponseEntity.ok(productSpecRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(ProductSpec::toDto)
                .filter(x -> !x.getName().isBlank()));
    }
}
