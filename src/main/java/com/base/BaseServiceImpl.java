package com.base;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLException;
import java.util.UUID;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseServiceImpl<TEntity extends Identifiable, TDto extends IdentifiableDTO, TFilter>
        implements BaseService<TEntity, TDto, TFilter> {
    protected static final String OBJECT_NOT_FOUND = "Объект не найден";
    private final JpaRepository<TEntity, UUID> repository;
    private final BaseMapper<TEntity, TDto> mapper;

    public BaseServiceImpl(JpaRepository<TEntity, UUID> repository, BaseMapper<TEntity, TDto> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TDto> findAll(Pageable pageable) {
        Page<TEntity> page = repository.findAll(pageable);
        return page.map(e -> mapper.toDTO(e));
    }

    @Override
    @Transactional(readOnly = true)
    public TDto get(UUID id) {
        return get(id, null);
    }

    @Transactional(readOnly = true)
    public TDto get(UUID id, @RequestParam MultiValueMap<String, String> params) {
        TEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(OBJECT_NOT_FOUND));
        return mapper.toDTO(entity);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public UUID create(TDto dto) {
        TEntity entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public UUID update(TDto dto) {
        TEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(OBJECT_NOT_FOUND));
        mapper.mergeToExistingEntity(entity, dto);
        entity = repository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
