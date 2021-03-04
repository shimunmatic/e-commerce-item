package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.CategoryCriteria;
import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.dto.UploadResult;
import com.shimunmatic.ecommerce.item.model.Category;
import com.shimunmatic.ecommerce.item.repository.CategoryRepository;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import com.shimunmatic.ecommerce.item.service.definition.CategoryService;
import com.shimunmatic.ecommerce.item.util.CDNUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Converter<Category, CategoryDTO> converter;
    private final String CDN_URL;

    public CategoryServiceImpl(CategoryRepository repository, Converter<Category, CategoryDTO> converter, @Value("${ecommerce.cdn-url}") String cdnUrl) {
        super(repository);
        this.categoryRepository = repository;
        this.converter = converter;
        CDN_URL = cdnUrl;
    }

    @Override
    public List<CategoryDTO> getAllDto(CategoryCriteria criteria) {
        List<Category> categories = criteria.getFlat() ? categoryRepository.findAllFlat() : getAll();
        return converter.toDto(categories);
    }

    @Override
    public CategoryDTO saveDTO(CategoryDTO dto) {
        return converter.toDto(save(converter.toModel(dto)));
    }

    @Override
    public void uploadThumbnail(Long categoryId, MultipartFile file) throws IOException {
        ResponseObject<UploadResult> responseObject = CDNUploadUtil.uploadMediaToCdn(String.format("%s/api/media/v1/media/categories/%d?thumbnail=true", CDN_URL, categoryId), file);

        if (responseObject != null && responseObject.isSuccess()) {
            UploadResult ur = responseObject.getData();
            log.info("Upload result: {}", ur);
            categoryRepository.updateThumbnail(categoryId, ur.getPath());
        } else {
            log.info("Error while saving thumbnail: {}", responseObject);
        }
    }

    @Override
    public CategoryDTO update(Long categoryId, CategoryDTO categoryDTO) {
        categoryDTO.setId(categoryId);
        return converter.toDto(update(converter.toModel(categoryDTO)));
    }
}
