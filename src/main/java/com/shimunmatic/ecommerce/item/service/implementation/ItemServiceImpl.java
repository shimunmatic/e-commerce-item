package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.Item;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import com.shimunmatic.ecommerce.item.repository.ItemRepository;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl extends AbstractService<Item, Long> implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemVariantService itemVariantService;
    private final Converter<Item, ItemDTO> itemConverter;
    private final Converter<ItemVariant, ItemVariantDTO> itemVariantConverter;


    public ItemServiceImpl(ItemRepository repository, ItemVariantService itemVariantService, Converter<Item, ItemDTO> itemConverter, Converter<ItemVariant, ItemVariantDTO> itemVariantConverter) {
        super(repository);
        this.itemRepository = repository;
        this.itemVariantService = itemVariantService;
        this.itemConverter = itemConverter;
        this.itemVariantConverter = itemVariantConverter;
    }

    @Override
    public List<ItemDTO> getItemsForCategoryDto(Long categoryId) {
        return itemConverter.toDto(itemRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public ItemDTO getDetailsDto(Long itemId) throws ResourceNotFoundException {
        Optional<Item> oi = getById(itemId);
        if (oi.isEmpty()) {
            log.info("Item resource not found: {}", itemId);
            throw new ResourceNotFoundException("No item found matching ID: " + itemId);
        }

        List<ItemVariant> variants = itemVariantService.getVariantsForItem(itemId);
        ItemDTO item = itemConverter.toDto(oi.get());
        item.setVariants(itemVariantConverter.toDto(variants));

        return item;
    }

    @Override
    public List<ItemDTO> getAllDto() {
        return itemConverter.toDto(getAll());
    }

    @Override
    public ItemDTO saveDto(ItemDTO dto) {
        return itemConverter.toDto(save(itemConverter.toModel(dto)));
    }
}
