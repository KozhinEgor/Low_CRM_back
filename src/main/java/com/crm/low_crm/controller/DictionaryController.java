package com.crm.low_crm.controller;

import com.clinic.entity.dictionary.*;
import com.clinic.repository.dictionary.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<?> getDoctors(@RequestParam(required = false, name = "doctorSpecs") List<String> specs){
        if (specs == null){
            return ResponseEntity.ok(doctorsRepository.findAll().stream().map(Doctor::toDto).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(doctorsRepository.findAllBySpecIn(specs).stream().map(Doctor::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/doctor-spec")
    public ResponseEntity<?> getDoctorSpecs(){
        return ResponseEntity.ok(doctorSpecRepository.findAll().stream().map(DoctorSpec::toDto));
    }
    @GetMapping("/unit")
    public ResponseEntity<?> getUnit(){
        return ResponseEntity.ok(unitRepository.findAll().stream().map(Unit::toDto).collect(Collectors.toList()));
    }
    @GetMapping("/product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(productRepository.findAll().stream().map(Product::toDto).collect(Collectors.toList()));
    }
    @GetMapping("/prouctSpec")
    public ResponseEntity<?> getProductSpec(){
        return ResponseEntity.ok(productSpecRepository.findAll().stream().map(ProductSpec::toDto).collect(Collectors.toList()));
    }
}
