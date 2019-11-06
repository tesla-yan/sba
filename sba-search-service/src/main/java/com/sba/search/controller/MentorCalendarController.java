package com.sba.search.controller;

import java.util.List;

import com.sba.search.model.MentorCalendarDtls;
import com.sba.search.service.MentorCalendarService;
import com.sba.security.util.Translator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/search")
@Api(value = "search-service", description = "Operations pertaining to mentor calendar services")
public class MentorCalendarController {

	@Autowired
	private MentorCalendarService mentorCalendarService;

	@ApiOperation(value = "Add calendar entry for specific mentor", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully performed the operation"),
			@ApiResponse(code = 302, message = "Resource Exist"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasRole('MENTOR')")
	@PostMapping("/addCalendarEntry/{mentorId}/{skillId}/{startDate}/{endDate}/{startTime}/{endTime}")
	public com.sba.security.model.ApiResponse<?> addCalendarEntry(
			@ApiParam(value = "Mentor Id for which record needs to find", required = true) @PathVariable(value = "mentorId", required = true) Long mentorId,
			@ApiParam(value = "Skill Id for which record needs to find", required = true) @PathVariable(value = "skillId", required = true) Long skillId,
			@ApiParam(value = "Start Date for which record needs to find", required = true) @PathVariable(value = "startDate", required = true) String startDate, 
			@ApiParam(value = "End Date for which record needs to find", required = true) @PathVariable(value = "endDate", required = true) String endDate,
			@ApiParam(value = "Start Time for which record needs to find", required = true) @PathVariable(value = "startTime", required = true) String startTime, 
			@ApiParam(value = "End Time for which record needs to find", required = true) @PathVariable(value = "endTime", required = true) String endTime) {

		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.mentor.calendar.add"),
				mentorCalendarService.addCalendarEntry(mentorId, skillId, startDate, endDate, startTime, endTime));
	}
	
	@ApiOperation(value = "View mentor calendar for specific mentor", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully performed the operation"),
			@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasRole('MENTOR')")
	@GetMapping("/findMentorCalendarByMentorId/{mentorId}/{startDate}/{endDate}")
	public List<MentorCalendarDtls> findMentorCalendarByMentorId(
			@ApiParam(value = "Mentor Id for which record needs to find", required = true) @PathVariable(value = "mentorId", required = true) Long mentorId,
			@ApiParam(value = "Start Date for which record needs to find", required = true) @PathVariable(value = "startDate") String startDate,
			@ApiParam(value = "End Date for which record needs to find", required = true) @PathVariable(value = "endDate") String endDate) {
		
		return mentorCalendarService.findMentorCalendarByMentorId(mentorId,startDate,endDate);
	}
	
}