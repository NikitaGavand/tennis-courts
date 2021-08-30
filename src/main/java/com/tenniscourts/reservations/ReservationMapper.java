package com.tenniscourts.reservations;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.ScheduleMapper;

@Mapper(
		unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
		componentModel = "spring", 
		uses= {ScheduleMapper.class,Guest.class})
public interface ReservationMapper {
	//ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    Reservation map(ReservationDTO source);

    @InheritInverseConfiguration
    ReservationDTO map(Reservation source);
    @Mappings({
    @Mapping(target = "guest.id", source = "guestId"),
    @Mapping(target = "schedule.id", source = "scheduleId")
    })
    Reservation map(CreateReservationRequestDTO source);
    
    default Reservation fromId(final Long id) {
        if (id == null) {
            return null;
        }
        final Reservation book = new Reservation();
        book.setId(id);
        return book;
    }
}
