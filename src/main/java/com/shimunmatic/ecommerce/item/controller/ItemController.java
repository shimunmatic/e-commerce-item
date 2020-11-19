package com.shimunmatic.ecommerce.item.controller;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.Item;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/item/item")
public class ItemController {
    private final ItemService itemService;
    private final Converter<Item, ItemDTO> converter;

    @Autowired
    public ItemController(ItemService itemService, Converter<Item, ItemDTO> converter) {
        this.itemService = itemService;
        this.converter = converter;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<ItemDTO>> getItems() throws Exception {
        log.info("...getItems...");
        List<Item> models = itemService.getAll();
        List<ItemDTO> dtos = converter.toDto(models);
        if (true) { throw new Exception("Exception in categories"); }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping(path = "category/{categoryId}")
    public ResponseEntity<List<ItemDTO>> getItemsForCategory(@PathVariable("categoryId") Long categoryId) {

        log.info("...getItemsForCategory...id: {}", categoryId);
        List<ItemDTO> dtos = itemService.getItemsForCategoryDto(categoryId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(path = "details/{itemId}")
    public ResponseEntity<ItemDTO> getItemDetails(@PathVariable("itemId") Long itemId) throws ResourceNotFoundException {
        log.info("...getItemDetails...id: {}", itemId);
        ItemDTO dto = itemService.getDetailsDto(itemId);
        if (dto == null) {
            log.info("...getItemDetails...id: {} Item not found", itemId);
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(dto);
    }
}
