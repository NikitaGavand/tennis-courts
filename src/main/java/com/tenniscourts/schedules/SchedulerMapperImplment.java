package com.tenniscourts.schedules;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.tenniscourts.tenniscourts.TennisCourt;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
@Mapper
@Component
public class SchedulerMapperImplment implements ScheduleMapper{

	@Override
	public Schedule map(ScheduleDTO source) {
		Schedule schedule = new Schedule();
		schedule.setId(source.getId());
		schedule.setStartDateTime(source.getStartDateTime());
		schedule.setEndDateTime(source.getEndDateTime());
		TennisCourtDTO dto = source.getTennisCourt();
		TennisCourt tennisCourt= new TennisCourt();
		tennisCourt.setId(dto.getId());
		schedule.setTennisCourt(tennisCourt);;
		return schedule;
	}

	@Override
	public ScheduleDTO map(Schedule source) {
		ScheduleDTO dto = new ScheduleDTO();
		dto.setId(source.getId());
		dto.setStartDateTime(source.getStartDateTime());
		dto.setEndDateTime(source.getEndDateTime());
		return dto;
	}

	@Override
	public List<ScheduleDTO> map(List<Schedule> source) {
		List<ScheduleDTO> list = new ArrayList<>();
		return list;
	}

	@Override
	public Schedule map(CreateScheduleRequestDTO createScheduleRequestDTO) {
		Schedule schedule = new Schedule();
		schedule.setId(createScheduleRequestDTO.getTennisCourtId());
		schedule.setStartDateTime(createScheduleRequestDTO.getStartDateTime());
		return schedule;
	}

}
