package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.model.ItemVariant;
import com.shimunmatic.ecommerce.item.repository.ItemVariantRepository;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemVariantServiceImpl extends AbstractService<ItemVariant, Long> implements ItemVariantService {
    private final ItemVariantRepository itemVariantRepository;

    public ItemVariantServiceImpl(ItemVariantRepository repository) {
        super(repository);
        this.itemVariantRepository = repository;
    }

    @Override
    public List<ItemVariant> getVariantsForItem(Long itemId) {
        return itemVariantRepository.findAllByItemId(itemId);
    }
}
