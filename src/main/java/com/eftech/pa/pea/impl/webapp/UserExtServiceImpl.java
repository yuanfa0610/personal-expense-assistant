package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.UserExtService;
import com.eftech.pa.pea.documentation.UserDocumentationConstants;
import com.eftech.pa.pea.dto.UserDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.User;
import com.eftech.pa.pea.impl.utils.ConversionService;
import com.eftech.pa.pea.impl.utils.DataService;
import com.eftech.pa.pea.utils.Generator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserExtServiceImpl implements UserExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = dataService.getAllUsers();
        return conversionService.convertToUserDTOs(users);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws ApiException {
        validateUserDTO(userDTO);

        User user = conversionService.convertToPersistedUser(userDTO);
        user.setEfId(Generator.generateEfId());
        user = dataService.saveUser(user);

        return conversionService.convertToUserDTO(user);
    }

    @Override
    public UserDTO getUser(String userId) throws ApiException {
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_ID_REQUIRED).wrap();
        }

        User user = dataService.getUserById(userId);
        if (user == null) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        return conversionService.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) throws ApiException {
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_ID_REQUIRED).wrap();
        }

        User existingUser = dataService.getUserById(userId);
        if (existingUser == null) {
            throw new ApiNotFoundExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        validateUserDTO(userDTO);

        User updatedUser = conversionService.convertToPersistedUser(userDTO);
        updatedUser = dataService.updateUser(updatedUser);

        return conversionService.convertToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(String userId) throws ApiException {
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_ID_REQUIRED).wrap();
        }

        User existingUser = dataService.getUserById(userId);
        if (existingUser == null) {
            throw new ApiNotFoundExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        dataService.deleteUser(existingUser);
    }

    /**
     * Validate user DTO
     * @param userDTO DTO to validate
     * @throws ApiException
     */
    private void validateUserDTO(UserDTO userDTO) throws ApiException {
        String lastName = userDTO.getLastName();
        String firstName = userDTO.getFirstName();
        if (StringUtils.isBlank(lastName)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_LAST_NAME_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(firstName)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_FIRST_NAME_REQUIRED).wrap();
        }
        String dateOfBirth = userDTO.getDateOfBirth();
        LocalDate localDate = Generator.generateLocalDate(dateOfBirth);
        if (dateOfBirth != null && localDate == null) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_DATE_OF_BIRTH_FORMAT_INVALID).wrap();
        }
        String email = userDTO.getEmail();
        if (email != null && !EmailValidator.getInstance().isValid(email)) {
            throw new ApiBadRequestExceptionBody(UserDocumentationConstants.ERROR_USER_EMAIL_FORMAT_INVALID).wrap();
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
