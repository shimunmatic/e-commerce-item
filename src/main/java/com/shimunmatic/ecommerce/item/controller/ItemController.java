package com.shimunmatic.ecommerce.item.controller;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/item/items", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ItemController {
    private final ItemService itemService;
    private final ItemVariantService itemVariantService;

    @Autowired
    public ItemController(ItemService itemService, ItemVariantService itemVariantService) {
        this.itemService = itemService;
        this.itemVariantService = itemVariantService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ItemDTO>> getItems(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        log.info("...getItems...");
        List<ItemDTO> dtos = categoryId == null ? itemService.getAllDto() : itemService.getItemsForCategoryDto(categoryId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(path = "{itemId}")
    public ResponseEntity<ItemDTO> getItemDetails(@PathVariable("itemId") Long itemId) throws ResourceNotFoundException {
        log.info("...getItemDetails...id: {}", itemId);
        ItemDTO dto = itemService.getDetailsDto(itemId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(path = "")
    public ResponseEntity<ItemDTO> saveNewItem(@RequestBody ItemDTO item) {
        log.info("...saveNewItem...: {}", item);
        ItemDTO dto = itemService.saveDto(item);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // item variants

    @GetMapping(path = "{itemId}/variants")
    public ResponseEntity<List<ItemVariantDTO>> getItemVariants(@PathVariable("itemId") Long itemId) {
        log.info("...getItemVariants...id: {}", itemId);
        List<ItemVariantDTO> dtos = itemVariantService.getVariantsForItemDto(itemId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(path = "{itemId}/variants/{variantId}")
    public ResponseEntity<ItemVariantDTO> getItemVariantDetails(@PathVariable("itemId") Long itemId, @PathVariable("variantId") Long variantId) throws ResourceNotFoundException {
        log.info("...getItemVariantDetails...id: {}, {}", itemId, variantId);
        ItemVariantDTO dto = itemVariantService.getDto(itemId, variantId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(path = "{itemId}/variants")
    public ResponseEntity<ItemVariantDTO> saveNewItemVariant(@PathVariable("itemId") Long itemId, @RequestBody ItemVariantDTO variant) {
        log.info("...saveNewItemVariant...: {}", variant);
        ItemVariantDTO dto = itemVariantService.saveDto(itemId, variant);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
