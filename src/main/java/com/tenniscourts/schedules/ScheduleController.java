package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@AllArgsConstructor
@RestController
@Api(tags = { "Schedule - Tennis Court" })
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @PostMapping(path="/schedule")
    @ApiOperation(value = "Add schedule", notes = "This service adds the schedule")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @GetMapping(path="/schedule")
    @ApiOperation(value = "Gets list of schedule from start date to emd Date", notes = "This service is gets schedule of the tennis court by the query params passed as startDate and endDate")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@QueryParam( "startDate")LocalDate startDate,
			@QueryParam( "endDate") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }
    
    @GetMapping(path="/{scheduleId}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get the schedule by Id", notes = "This service is gets the details of schedule by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<ScheduleDTO> findByScheduleId(Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
