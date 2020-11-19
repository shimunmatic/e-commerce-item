package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.model.ItemVariant;

import java.util.List;

public interface ItemVariantService extends CRUDService<ItemVariant, Long> {

    List<ItemVariant> getVariantsForItem(Long itemId);
}
