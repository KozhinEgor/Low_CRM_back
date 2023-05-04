package com.base;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

import java.util.UUID;


public interface BaseService<TEntity extends Identifiable, TDto extends IdentifiableDTO, TFilter> {
    Page<TDto> findAll(Pageable p);

    default TDto get(UUID id) {
        return get(id, null);
    }

    TDto get(UUID id, MultiValueMap<String, String> params);
    UUID create(TDto dto);
    UUID update(TDto dto);
    void delete(UUID id);
    Page<TDto> findAllByFilter(TFilter filter, Pageable pageable);
}
