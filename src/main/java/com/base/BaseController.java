package com.base;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
public abstract class BaseController<TEntity extends Identifiable, TDto extends IdentifiableDTO, TFilter> {
    private final BaseService<TEntity, TDto, TFilter> service;

    public BaseController(BaseService<TEntity, TDto, TFilter> service) {
        this.service = service;
    }

    @Operation(summary = "Получить список объектов (пагинация)")
    @GetMapping
    public ResponseEntity<Page<TDto>> list(Pageable pageable) {
        return new ResponseEntity(service.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Получить объект по id")
    @GetMapping("/{id}")
    public ResponseEntity<TDto> getById(@PathVariable("id") @NotNull UUID id,
                                        @RequestParam(required=false) MultiValueMap<String, String> params) {
        return new ResponseEntity(service.get(id, params), HttpStatus.OK);
    }

    @Operation(summary = "Создание объекта")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody TDto entry) {
        return new ResponseEntity(service.create(entry), HttpStatus.OK);
    }

    @Operation(summary = "Обновление объекта")
    @PutMapping
    public ResponseEntity<Long> update(@RequestBody TDto entry) {
        try {
            return new ResponseEntity(service.update(entry), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ошибка обновления объекта", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Получить список объектов c фильтром (пагинация)")
    @PostMapping("/filter")
    public ResponseEntity<Page<TDto>> findAllByFilter(@RequestBody TFilter filter, Pageable pageable) {
        try {
            return new ResponseEntity(service.findAllByFilter(filter, pageable), HttpStatus.OK);
        } catch (Exception e) {
            log.error("не удалось загрузить список объектов", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

