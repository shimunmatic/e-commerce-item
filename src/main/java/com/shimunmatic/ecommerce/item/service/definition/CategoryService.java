package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.dto.CategoryCriteria;
import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.dto.ItemMediaType;
import com.shimunmatic.ecommerce.item.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService extends CRUDService<Category, Long> {

    List<CategoryDTO> getAllDto(CategoryCriteria criteria);

    CategoryDTO saveDTO(CategoryDTO dto);

    void uploadThumbnail(Long categoryId, MultipartFile file) throws IOException;

    CategoryDTO update(Long categoryId, CategoryDTO categoryDTO);
}
