package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.CreditCardExtService;
import com.eftech.pa.pea.documentation.CreditCardDocumentationConstants;
import com.eftech.pa.pea.documentation.UserDocumentationConstants;
import com.eftech.pa.pea.dto.CreditCardDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiConflictExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.CreditCard;
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
@RequestMapping("/creditCard")
public class CreditCardExtServiceImpl implements CreditCardExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<CreditCardDTO> getCreditCards() {
        List<CreditCard> creditCards = dataService.getAllCreditCards();
        return conversionService.convertToCreditCardDTOs(creditCards);
    }

    @Override
    public CreditCardDTO createCreditCard(CreditCardDTO creditCardDTO) throws ApiException {
        validateCreditCardDTO(creditCardDTO);

        String userId = creditCardDTO.getUserId();
        User user = dataService.getUserById(userId);
        if (user == null) {
            throw new ApiNotFoundExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        String name = creditCardDTO.getName();
        CreditCard creditCard = dataService.getCreditCardByUserIdAndCardName(userId, name);
        if (creditCard != null) {
            throw new ApiConflictExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NAME_DUPLICATE).wrap();
        }

        creditCard = conversionService.convertToPersistedCreditCard(creditCardDTO);
        creditCard.setEfId(Generator.generateEfId());
        creditCard.setUser(user);
        creditCard = dataService.saveCreditCard(creditCard);

        return conversionService.convertToCreditCardDTO(creditCard);
    }

    @Override
    public CreditCardDTO getCreditCard(String creditCardId) throws ApiException {
        if (StringUtils.isBlank(creditCardId)) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_ID_REQUIRED).wrap();
        }

        CreditCard creditCard = dataService.getCreditCardById(creditCardId);
        if (creditCard == null) {
            throw new ApiNotFoundExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NOT_FOUND).wrap();
        }

        return conversionService.convertToCreditCardDTO(creditCard);
    }

    @Override
    public CreditCardDTO updateCreditCard(String creditCardId, CreditCardDTO creditCardDTO) throws ApiException {
        if (StringUtils.isBlank(creditCardId)) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_ID_REQUIRED).wrap();
        }

        CreditCard existingCreditCard = dataService.getCreditCardById(creditCardId);
        if (existingCreditCard == null) {
            throw new ApiNotFoundExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NOT_FOUND).wrap();
        }

        validateCreditCardDTO(creditCardDTO);

        String userId = creditCardDTO.getUserId();
        String name = creditCardDTO.getName();
        if (!StringUtils.equals(userId, existingCreditCard.getUser().getEfId())) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_USER_ID_NOT_MATCH).wrap();
        }
        if (!StringUtils.equals(name, existingCreditCard.getName()) && dataService.getCategoryByUserIdAndCategoryName(userId, name) != null) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NAME_DUPLICATE).wrap();
        }

        CreditCard updatedCreditCard = conversionService.convertToPersistedCreditCard(creditCardDTO);
        updatedCreditCard.setUser(existingCreditCard.getUser());
        updatedCreditCard = dataService.updateCreditCard(updatedCreditCard);

        return conversionService.convertToCreditCardDTO(updatedCreditCard);
    }

    @Override
    public void deleteCreditCard(String creditCardId) throws ApiException {
        if (StringUtils.isBlank(creditCardId)) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_ID_REQUIRED).wrap();
        }

        CreditCard existingCreditCard = dataService.getCreditCardById(creditCardId);
        if (existingCreditCard == null) {
            throw new ApiNotFoundExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NOT_FOUND).wrap();
        }

        dataService.deleteCreditCard(existingCreditCard);
    }

    /**
     * Validate credit card DTO
     * @param creditCardDTO DTO to validate
     * @throws ApiException
     */
    private void validateCreditCardDTO(CreditCardDTO creditCardDTO) throws ApiException {
        String userId = creditCardDTO.getUserId();
        String name = creditCardDTO.getName();
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_USER_ID_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(name)) {
            throw new ApiBadRequestExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NAME_REQUIRED).wrap();
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
