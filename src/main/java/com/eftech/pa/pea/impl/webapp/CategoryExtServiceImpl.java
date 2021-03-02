package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.CategoryExtService;
import com.eftech.pa.pea.documentation.CategoryDocumentationConstants;
import com.eftech.pa.pea.documentation.UserDocumentationConstants;
import com.eftech.pa.pea.dto.CategoryDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiConflictExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.Category;
import com.eftech.pa.pea.impl.persistent.User;
import com.eftech.pa.pea.impl.utils.ConversionService;
import com.eftech.pa.pea.impl.utils.DataService;
import com.eftech.pa.pea.utils.Generator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryExtServiceImpl implements CategoryExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = dataService.getAllCategories();
        return conversionService.convertToCategoryDTOs(categories);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws ApiException {
        validateCategoryDTO(categoryDTO);

        String userId = categoryDTO.getUserId();
        User user = dataService.getUserById(userId);
        if (user == null) {
            throw new ApiNotFoundExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        String name = categoryDTO.getName();
        Category category = dataService.getCategoryByUserIdAndCategoryName(userId, name);
        if (category != null) {
            throw new ApiConflictExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NAME_DUPLICATE).wrap();
        }

        category = conversionService.convertToPersistedCategory(categoryDTO);
        category.setEfId(Generator.generateEfId());
        category.setUser(user);
        category = dataService.saveCategory(category);

        return conversionService.convertToCategoryDTO(category);
    }

    @Override
    public CategoryDTO getCategory(String categoryId) throws ApiException {
        if (StringUtils.isBlank(categoryId)) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_ID_REQUIRED).wrap();
        }

        Category category = dataService.getCategoryById(categoryId);
        if (category == null) {
            throw new ApiNotFoundExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NOT_FOUND).wrap();
        }

        return conversionService.convertToCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(String categoryId, CategoryDTO categoryDTO) throws ApiException {
        if (StringUtils.isBlank(categoryId)) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_ID_REQUIRED).wrap();
        }
        Category existingCategory = dataService.getCategoryById(categoryId);
        if (existingCategory == null) {
            throw new ApiNotFoundExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NOT_FOUND).wrap();
        }

        validateCategoryDTO(categoryDTO);

        String userId = categoryDTO.getUserId();
        String name = categoryDTO.getName();
        if (!StringUtils.equals(userId, existingCategory.getUser().getEfId())) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_USER_ID_NOT_MATCH).wrap();
        }

        if (!StringUtils.equals(name, existingCategory.getName()) && dataService.getCategoryByUserIdAndCategoryName(userId, name) != null) {
            throw new ApiConflictExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NAME_DUPLICATE).wrap();
        }

        Category updatedCategory = conversionService.convertToPersistedCategory(categoryDTO);
        updatedCategory.setUser(existingCategory.getUser());
        updatedCategory = dataService.updateCategory(updatedCategory);

        return conversionService.convertToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) throws ApiException {
        if (StringUtils.isBlank(categoryId)) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_ID_REQUIRED).wrap();
        }

        Category existingCategory = dataService.getCategoryById(categoryId);
        if (existingCategory == null) {
            throw new ApiNotFoundExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NOT_FOUND).wrap();
        }

        dataService.deleteCategory(existingCategory);
    }

    /**
     * Validate category DTO
     * @param categoryDTO DTO to validate
     * @throws ApiException
     */
    private void validateCategoryDTO(CategoryDTO categoryDTO) throws ApiException {
        String userId = categoryDTO.getUserId();
        String name = categoryDTO.getName();
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_USER_ID_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(name)) {
            throw new ApiBadRequestExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NAME_REQUIRED).wrap();
        }
    }

    /**
     * @param dataService the data service to set
     */
    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * @param conversionService the data service to set
     */
    @Autowired
    public void setDataService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
