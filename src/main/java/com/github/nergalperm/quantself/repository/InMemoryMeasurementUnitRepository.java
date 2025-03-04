package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class InMemoryMeasurementUnitRepository implements MeasurementUnitRepository {
    private final Map<Long, MeasurementUnit> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    @NonNull
    public <S extends MeasurementUnit> S save(@NonNull S entity) {
        if (entity.getId() == null) {
            entity.setId(idSequence.getAndIncrement());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    @NonNull
    public Optional<MeasurementUnit> findById(@NonNull Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(@NonNull Long id) {
        storage.remove(id);
    }

    @Override
    public void delete(@NonNull MeasurementUnit entity) {
        if (entity.getId() != null) {
            storage.remove(entity.getId());
        }
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAll(@NonNull Sort sort) {
        return findAll();
    }

    @Override
    @NonNull
    public Page<MeasurementUnit> findAll(@NonNull Pageable pageable) {
        throw new UnsupportedOperationException("Pagination not supported in stub");
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> List<S> saveAll(@NonNull Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        entities.forEach(e -> saved.add(save(e)));
        return saved;
    }

    @Override
    public boolean existsById(@NonNull Long id) {
        return storage.containsKey(id);
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAllById(@NonNull Iterable<Long> ids) {
        List<MeasurementUnit> result = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends Long> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends MeasurementUnit> entities) {
        entities.forEach(this::delete);
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> Optional<S> findOne(@NonNull Example<S> example) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> List<S> findAll(@NonNull Example<S> example) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> List<S> findAll(@NonNull Example<S> example, @NonNull Sort sort) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> Page<S> findAll(@NonNull Example<S> example, @NonNull Pageable pageable) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    public <S extends MeasurementUnit> long count(@NonNull Example<S> example) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    public <S extends MeasurementUnit> boolean exists(@NonNull Example<S> example) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit, R> R findBy(@NonNull Example<S> example, @NonNull Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Example matching not supported in stub");
    }
}