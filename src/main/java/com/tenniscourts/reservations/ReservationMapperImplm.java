package com.tenniscourts.reservations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleDTO;

@Component
@Mapper
public class ReservationMapperImplm implements ReservationMapper {

	@Override
	public Reservation map(ReservationDTO source) {
		if (source == null) {
			return null;
		}
		Reservation reservation = new Reservation();
		Guest guest = new Guest();
		guest.setId(source.getGuestId());
		reservation.setGuest(guest);
		reservation.setRefundValue(source.getRefundValue());
		
		return reservation;
	}

	@Override
	public ReservationDTO map(Reservation source) {
		if (source == null) {
			return null;
		}
		ReservationDTO reservation = new ReservationDTO();
		Guest guest = source.getGuest();
		reservation.setGuestId(guest.getId());
		Schedule schedule = source.getSchedule();
		ScheduleDTO dto = new ScheduleDTO();
		dto.setId(schedule.getId());
		dto.setEndDateTime(schedule.getEndDateTime());
		dto.setStartDateTime(schedule.getStartDateTime());
		reservation.setSchedule(dto);
		reservation.setRefundValue(source.getRefundValue());
		reservation.setReservationStatus(source.getReservationStatus().toString());
		return reservation;
	}

	@Override
	public Reservation map(CreateReservationRequestDTO source) {
		if (source == null) {
			return null;
		}
		Reservation reservation = new Reservation();
		LocalDateTime now = LocalDateTime.now();
		reservation.setDateCreate(now);
	
		Guest guest = new Guest();
		guest.setId(source.getGuestId());
		reservation.setGuest(guest);
		Schedule schedule= new Schedule();
		schedule.setId(source.getScheduleId());
		reservation.setSchedule(schedule);
		BigDecimal value = new BigDecimal(1233);
		reservation.setValue(value);
		return reservation;
	}

}
