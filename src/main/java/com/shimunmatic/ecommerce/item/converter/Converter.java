package com.shimunmatic.ecommerce.item.converter;

import java.util.ArrayList;
import java.util.List;

public interface Converter<MODEL, DTO> {

    DTO toDto(MODEL model);

    default List<DTO> toDto(Iterable<MODEL> models) {
        if (models == null) return null;
        List<DTO> dtos = new ArrayList<>();
        models.forEach(model -> dtos.add(toDto(model)));
        return dtos;
    }

    MODEL toModel(DTO dto);

    default List<MODEL> toModel(Iterable<DTO> dtos) {
        if (dtos == null) return null;
        List<MODEL> models = new ArrayList<>();
        dtos.forEach(dto -> models.add(toModel(dto)));
        return models;
    }
}