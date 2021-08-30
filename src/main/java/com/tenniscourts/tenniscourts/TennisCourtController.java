package com.tenniscourts.tenniscourts;

import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Api(tags = { "Tennis Court" },value="This controller helps to perform create update delete operations on tennis court")
@RequestMapping("/tennis")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Addition of Tennis Court", notes = "This service is adds the tennis court")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<Void> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
    	System.out.println("tennis");
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @GetMapping(path="/{tennisCourtId}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get details of Tennis Court by Id", notes = "This service is finds the tennis court")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable("tennisCourtId")Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @GetMapping
    @ApiOperation(value = "Finds the Tennis court schedules by ID", notes = "This service is finds the tennis court")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerErrorException.class) })
	public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@QueryParam("tennisCourtId")Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
