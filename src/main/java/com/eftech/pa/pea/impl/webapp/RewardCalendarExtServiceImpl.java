package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.RewardCalendarExtService;
import com.eftech.pa.pea.documentation.CategoryDocumentationConstants;
import com.eftech.pa.pea.documentation.CreditCardDocumentationConstants;
import com.eftech.pa.pea.documentation.RewardCalendarDocumentationConstants;
import com.eftech.pa.pea.dto.RewardCalendarDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiConflictExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.Category;
import com.eftech.pa.pea.impl.persistent.CreditCard;
import com.eftech.pa.pea.impl.persistent.RewardCalendar;
import com.eftech.pa.pea.impl.utils.ConversionService;
import com.eftech.pa.pea.impl.utils.DataService;
import com.eftech.pa.pea.utils.Generator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rewardCalendar")
public class RewardCalendarExtServiceImpl implements RewardCalendarExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<RewardCalendarDTO> getRewardCalendars() {
        List<RewardCalendar> rewardCalendars = dataService.getAllRewardCalendars();
        return conversionService.convertToRewardCalendarDTOs(rewardCalendars);
    }

    @Override
    public List<RewardCalendarDTO> getRewardCalendars(String creditCardId, String categoryId) {
        List<RewardCalendar> rewardCalendars = dataService.getRewardCalendarsByCardIdAndCategoryId(creditCardId, categoryId);
        return conversionService.convertToRewardCalendarDTOs(rewardCalendars);
    }

    @Override
    public RewardCalendarDTO getRewardCalendar(String rewardCalendarId) throws ApiException {
        if (StringUtils.isBlank(rewardCalendarId)) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_ID_REQUIRED).wrap();
        }

        RewardCalendar rewardCalendar = dataService.getRewardCalendarById(rewardCalendarId);
        if (rewardCalendar == null) {
            throw new ApiNotFoundExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_NOT_FOUND).wrap();
        }

        return conversionService.convertToRewardCalendarDTO(rewardCalendar);
    }

    @Override
    public RewardCalendarDTO createRewardCalendar(RewardCalendarDTO rewardCalendarDTO) throws ApiException {
        validateRewardCalendarDTO(rewardCalendarDTO);

        String creditCardId = rewardCalendarDTO.getCreditCardId();
        CreditCard creditCard = dataService.getCreditCardById(creditCardId);
        if (creditCard == null) {
            throw new ApiNotFoundExceptionBody(CreditCardDocumentationConstants.ERROR_CREDIT_CARD_NOT_FOUND).wrap();
        }

        String categoryId = rewardCalendarDTO.getCategoryId();
        Category category = dataService.getCategoryById(categoryId);
        if (category == null) {
            throw new ApiNotFoundExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NOT_FOUND).wrap();
        }

        if (!creditCard.getUser().getEfId().equals(category.getUser().getEfId())) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_USER_NOT_MATCH).wrap();
        }

        Month month = rewardCalendarDTO.getMonth();
        RewardCalendar rewardCalendar = dataService.getRewardCalendarByCardCategoryAndMonth(creditCardId, categoryId, month);
        if (rewardCalendar != null) {
            throw new ApiConflictExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_DUPLICATE).wrap();
        }

        rewardCalendar = conversionService.convertToPersistedRewardCalendar(rewardCalendarDTO);
        rewardCalendar.setEfId(Generator.generateEfId());
        rewardCalendar.setCreditCard(creditCard);
        rewardCalendar.setCategory(category);
        rewardCalendar = dataService.saveRewardCalendar(rewardCalendar);

        return conversionService.convertToRewardCalendarDTO(rewardCalendar);
    }

    @Override
    public List<RewardCalendarDTO> createRewardCalendars(List<RewardCalendarDTO> rewardCalendarDTOs) {
        List<RewardCalendarDTO> rewardCalendarDTOSToReturn = new ArrayList<>();
        for (RewardCalendarDTO rcDTO : rewardCalendarDTOs) {
            try {
                RewardCalendarDTO rewardCalendarDTO = createRewardCalendar(rcDTO);
                rewardCalendarDTOSToReturn.add(rewardCalendarDTO);
            } catch (ApiException apiException) {
                // TODO: if we need to deal with multi-exceptions
            }
        }
        return rewardCalendarDTOSToReturn;
    }

    @Override
    public RewardCalendarDTO updateRewardCalendar(String rewardCalendarId, RewardCalendarDTO rewardCalendarDTO) throws ApiException {
        if (StringUtils.isBlank(rewardCalendarId)) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_ID_REQUIRED).wrap();
        }

        RewardCalendar existingRewardCalendar = dataService.getRewardCalendarById(rewardCalendarId);
        if (existingRewardCalendar == null) {
            throw new ApiNotFoundExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_NOT_FOUND).wrap();
        }

        validateRewardCalendarDTO(rewardCalendarDTO);

        String creditCardId = rewardCalendarDTO.getCreditCardId();
        String categoryId = rewardCalendarDTO.getCategoryId();
        Month month = rewardCalendarDTO.getMonth();
        if (!StringUtils.equals(creditCardId, existingRewardCalendar.getCreditCard().getEfId())) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_CREDIT_CARD_ID_NOT_MATCH).wrap();
        }
        if (!StringUtils.equals(categoryId, existingRewardCalendar.getCategory().getEfId())) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_CATEGORY_ID_NOT_MATCH).wrap();
        }
        if (month.getValue() != existingRewardCalendar.getMonth().getValue()) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_MONTH_NOT_MATCH).wrap();
        }

        RewardCalendar updatedRewardCalendar = conversionService.convertToPersistedRewardCalendar(rewardCalendarDTO);
        updatedRewardCalendar.setCreditCard(existingRewardCalendar.getCreditCard());
        updatedRewardCalendar.setCategory(existingRewardCalendar.getCategory());
        updatedRewardCalendar.setMonth(existingRewardCalendar.getMonth());
        updatedRewardCalendar = dataService.updateRewardCalender(updatedRewardCalendar);

        return conversionService.convertToRewardCalendarDTO(updatedRewardCalendar);
    }

    @Override
    public void deleteRewardCalendar(String rewardCalendarId) throws ApiException {
        if (StringUtils.isBlank(rewardCalendarId)) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_ID_REQUIRED).wrap();
        }

        RewardCalendar existingRewardCalendar = dataService.getRewardCalendarById(rewardCalendarId);
        if (existingRewardCalendar == null) {
            throw new ApiNotFoundExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_NOT_FOUND).wrap();
        }

        dataService.deleteRewardCalendar(existingRewardCalendar);
    }

    /**
     * Validate reward calendar DTO
     * @param rewardCalendarDTO DTO to validate
     * @throws ApiException
     */
    private void validateRewardCalendarDTO(RewardCalendarDTO rewardCalendarDTO) throws ApiException {
        String creditCardId = rewardCalendarDTO.getCreditCardId();
        String categoryId = rewardCalendarDTO.getCategoryId();
        Month month = rewardCalendarDTO.getMonth();
        if (StringUtils.isBlank(creditCardId)) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_CREDIT_CARD_ID_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(categoryId)) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_CATEGORY_ID_REQUIRED).wrap();
        }
        if (month == null) {
            throw new ApiBadRequestExceptionBody(RewardCalendarDocumentationConstants.ERROR_REWARD_CALENDAR_MONTH_REQUIRED).wrap();
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
