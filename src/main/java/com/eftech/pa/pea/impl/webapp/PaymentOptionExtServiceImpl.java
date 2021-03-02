package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.PaymentOptionExtService;
import com.eftech.pa.pea.documentation.PaymentOptionDocumentationConstants;
import com.eftech.pa.pea.dto.PaymentOptionDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiConflictExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.PaymentOption;
import com.eftech.pa.pea.impl.utils.ConversionService;
import com.eftech.pa.pea.impl.utils.DataService;
import com.eftech.pa.pea.utils.Generator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paymentOption")
public class PaymentOptionExtServiceImpl implements PaymentOptionExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<PaymentOptionDTO> getPaymentOptions() {
        List<PaymentOption> paymentOptions = dataService.getAllPaymentOptions();
        return conversionService.convertToPaymentOptionDTOs(paymentOptions);
    }

    @Override
    public PaymentOptionDTO createPaymentOption(PaymentOptionDTO paymentOptionDTO) throws ApiException {
        validatePaymentOptionDTO(paymentOptionDTO);

        String name = paymentOptionDTO.getName();
        PaymentOption paymentOption = dataService.getPaymentOptionByName(name);
        if (paymentOption != null) {
            throw new ApiConflictExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_NAME_DUPLICATE).wrap();
        }

        paymentOption = conversionService.convertToPersistedPaymentOption(paymentOptionDTO);
        paymentOption.setEfId(Generator.generateEfId());
        paymentOption = dataService.savePaymentOption(paymentOption);

        return conversionService.convertToPaymentOptionDTO(paymentOption);
    }

    @Override
    public PaymentOptionDTO getPaymentOption(String paymentOptionId) throws ApiException {
        if (StringUtils.isBlank(paymentOptionId)) {
            throw new ApiBadRequestExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_ID_REQUIRED).wrap();
        }

        PaymentOption paymentOption = dataService.getPaymentOptionById(paymentOptionId);
        if (paymentOption == null) {
            throw new ApiNotFoundExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_NOT_FOUND).wrap();
        }

        return conversionService.convertToPaymentOptionDTO(paymentOption);
    }

    @Override
    public void deletePaymentOption(String paymentOptionId) throws ApiException {
        if (StringUtils.isBlank(paymentOptionId)) {
            throw new ApiBadRequestExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_ID_REQUIRED).wrap();
        }

        PaymentOption existingPaymentOption = dataService.getPaymentOptionById(paymentOptionId);
        if (existingPaymentOption == null) {
            throw new ApiNotFoundExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_NOT_FOUND).wrap();
        }

        dataService.deletePaymentOption(existingPaymentOption);
    }

    /**
     * Validate payment option DTO
     * @param paymentOptionDTO DTO to validate
     * @throws ApiException
     */
    private void validatePaymentOptionDTO(PaymentOptionDTO paymentOptionDTO) throws ApiException {
        String name = paymentOptionDTO.getName();
        if (StringUtils.isBlank(name)) {
            throw new ApiBadRequestExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_NAME_REQUIRED).wrap();
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
