package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.DiscountDTO;
import com.shimunmatic.ecommerce.item.model.Discount;
import org.springframework.stereotype.Component;

@Component
public class DiscountConverter implements Converter<Discount, DiscountDTO> {

    @Override
    public DiscountDTO toDto(Discount discount) {
        return DiscountDTO.builder()
                .id(discount.getId())
                .name(discount.getName())
                .description(discount.getDescription())
                .active(discount.getActive())
                .dateValidFrom(discount.getDateValidFrom())
                .dateValidTo(discount.getDateValidTo())
                .discountValueType(discount.getDiscountValueType())
                .value(discount.getValue())
                .build();
    }

    @Override
    public Discount toModel(DiscountDTO discountDTO) {
        Discount discount = new Discount(discountDTO.getName(), discountDTO.getDescription(), discountDTO.getActive(), discountDTO.getValue(), discountDTO.getDiscountValueType(), discountDTO.getDateValidFrom(), discountDTO.getDateValidTo());
        discount.setId(discountDTO.getId());

        return discount;
    }
}
