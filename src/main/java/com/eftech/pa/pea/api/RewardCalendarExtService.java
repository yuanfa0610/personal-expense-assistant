package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.RewardCalendarDTO;
import com.eftech.pa.pea.exception.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface RewardCalendarExtService {

    /**
     * Retrieve all reward calendars
     * @return a list of reward calendar DTOs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<RewardCalendarDTO> getRewardCalendars();

    /**
     * Retrieve reward calendars that satisfy given conditions
     * @return a list of reward calendar DTOs
     */
    @RequestMapping(path = "/creditCard/{creditCardId}/category/{categoryId}", method = RequestMethod.GET)
    List<RewardCalendarDTO> getRewardCalendars(@PathVariable("creditCardId") String creditCardId,
                                               @PathVariable("categoryId") String categoryId);

    /**
     * Retrieve reward calendar with specified id
     * @param rewardCalendarId id of the reward calendar
     * @return DTO of the requested reward calendar
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{rewardCalendarId}", method = RequestMethod.GET)
    RewardCalendarDTO getRewardCalendar(@PathVariable("rewardCalendarId") String rewardCalendarId) throws ApiException;

    /**
     * Create a new reward calendar
     * @param rewardCalendarDTO DTO of the reward calendar to create
     * @return DTO of the created reward calendar
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    RewardCalendarDTO createRewardCalendar(@RequestBody RewardCalendarDTO rewardCalendarDTO) throws ApiException;

    /**
     * Create a list of new reward calendar
     * @param rewardCalendarDTOs DTOs of the reward calendars to create
     * @return DTOs of the created reward calendars
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/multiple", method = RequestMethod.POST)
    List<RewardCalendarDTO> createRewardCalendars(@RequestBody List<RewardCalendarDTO> rewardCalendarDTOs) throws ApiException;
    /**
     * Update an existing reward calendar
     * @param rewardCalendarId id of the target reward calendar
     * @param rewardCalendarDTO DTO of the reward calendar to update
     * @return DTO of the updated reward calendar
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.PUT)
    RewardCalendarDTO updateRewardCalendar(@PathVariable("rewardCalendarId") String rewardCalendarId, @RequestBody RewardCalendarDTO rewardCalendarDTO) throws ApiException;

    /**
     * Delete reward calendar with specified id
     * @param rewardCalendarId id of the target reward calendar
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{rewardCalendarId}", method = RequestMethod.DELETE)
    void deleteRewardCalendar(@PathVariable("rewardCalendarId") String rewardCalendarId) throws ApiException;

}
