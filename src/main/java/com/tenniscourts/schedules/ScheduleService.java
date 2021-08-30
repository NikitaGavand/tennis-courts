package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

	@Autowired
    private final ScheduleRepository scheduleRepository;
	@Autowired
    private final ScheduleMapper scheduleMapper;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
    	ScheduleDTO respObject = scheduleMapper.map(scheduleRepository.save(scheduleMapper.map(createScheduleRequestDTO)));
        return respObject;
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
    	Iterable<Long> ids = null;
		List<ScheduleDTO> schedules = scheduleMapper.map(scheduleRepository.findAllById(ids));
        return schedules;
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
    	 return scheduleRepository.findById(scheduleId).map(scheduleMapper::map).<EntityNotFoundException>orElseThrow(() -> {
            throw new EntityNotFoundException("Schedule not found.");
        });
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
