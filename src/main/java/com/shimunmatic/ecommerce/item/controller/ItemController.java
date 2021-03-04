package com.shimunmatic.ecommerce.item.controller;

import com.shimunmatic.ecommerce.item.dto.ItemCriteria;
import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemMediaType;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/item/v1/items", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ItemController {
    private final ItemService itemService;
    private final ItemVariantService itemVariantService;

    @Autowired
    public ItemController(ItemService itemService, ItemVariantService itemVariantService) {
        this.itemService = itemService;
        this.itemVariantService = itemVariantService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ResponseObject<Page<ItemDTO>>> getItems(ItemCriteria itemCriteria) {
        log.info("...getItems...");
        Page<ItemDTO> dtos = itemService.getAll(itemCriteria);
        return ResponseEntity.ok(ResponseObject.ofData(dtos));
    }

    @GetMapping(path = "{itemId}")
    public ResponseEntity<ResponseObject<ItemDTO>> getItemDetails(@PathVariable("itemId") Long itemId) throws ResourceNotFoundException {
        log.info("...getItemDetails...id: {}", itemId);
        ItemDTO dto = itemService.getDetailsDto(itemId);
        return ResponseEntity.ok(ResponseObject.ofData(dto));
    }

    @PostMapping(path = "")
    public ResponseEntity<ResponseObject<ItemDTO>> saveNewItem(@RequestBody ItemDTO item) {
        log.info("...saveNewItem...: {}", item);
        ItemDTO dto = itemService.saveDto(item);
        return new ResponseEntity<>(ResponseObject.ofData(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{itemId}")
    public ResponseEntity<ResponseObject<ItemDTO>> updateItem(@PathVariable("itemId") Long itemId, @RequestBody ItemDTO itemDTO) {
        log.info("...updateItem...{}", itemDTO);

        ItemDTO savedDTO = itemService.update(itemId, itemDTO);

        return ResponseEntity.ok(ResponseObject.ofData(savedDTO));
    }

    // item variants

    @GetMapping(path = "{itemId}/variants")
    public ResponseEntity<ResponseObject<List<ItemVariantDTO>>> getItemVariants(@PathVariable("itemId") Long itemId) {
        log.info("...getItemVariants...id: {}", itemId);
        List<ItemVariantDTO> dtos = itemVariantService.getVariantsForItemDto(itemId);
        return ResponseEntity.ok(ResponseObject.ofData(dtos));
    }

    @GetMapping(path = "{itemId}/variants/{variantId}")
    public ResponseEntity<ResponseObject<ItemVariantDTO>> getItemVariantDetails(@PathVariable("itemId") Long itemId, @PathVariable("variantId") Long variantId) throws ResourceNotFoundException {
        log.info("...getItemVariantDetails...id: {}, {}", itemId, variantId);
        ItemVariantDTO dto = itemVariantService.getDto(itemId, variantId);
        return ResponseEntity.ok(ResponseObject.ofData(dto));
    }

    @PostMapping(path = "{itemId}/variants")
    public ResponseEntity<ResponseObject<ItemVariantDTO>> saveNewItemVariant(@PathVariable("itemId") Long itemId, @RequestBody ItemVariantDTO variant) {
        log.info("...saveNewItemVariant...: {}", variant);
        ItemVariantDTO dto = itemVariantService.saveDto(itemId, variant);
        return new ResponseEntity<>(ResponseObject.ofData(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{itemId}/variants/{variantId}")
    public ResponseEntity<ResponseObject<ItemVariantDTO>> updateItemVariant(@PathVariable("itemId") Long itemId, @PathVariable("variantId") Long variantId,
                                                                            @RequestBody ItemVariantDTO itemVariantDTO) {
        log.info("...updateItemVariant...{}", itemVariantDTO);

        ItemVariantDTO savedDTO = itemVariantService.update(itemId, itemVariantDTO);

        return ResponseEntity.ok(ResponseObject.ofData(savedDTO));
    }

    @PostMapping(path = "{itemId}/media")
    public ResponseEntity<?> uploadFile(@PathVariable("itemId") Long itemId, @RequestParam("mediaType") ItemMediaType mediaType, @RequestParam("file") MultipartFile file) throws IOException {
        itemService.uploadFile(itemId, mediaType, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "{itemId}/thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable("itemId") Long itemId, @RequestParam("file") MultipartFile file) throws IOException {
        itemService.uploadThumbnail(itemId, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/variants/{variantId}/thumbnail")
    public ResponseEntity<?> uploadVariantThumbnail(@PathVariable("variantId") Long itemVariantId, @RequestParam("file") MultipartFile file) throws IOException {
        itemVariantService.uploadThumbnail(itemVariantId, file);
        return ResponseEntity.ok().build();
    }
}
