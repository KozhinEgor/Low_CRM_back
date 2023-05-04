package com.base;

public interface BaseMapper<TEntity, TDto> {
    TDto toDTO(TEntity entity);
    TEntity toEntity(TDto dto);
    void mergeToExistingEntity(TEntity entity, TDto dto);
}
