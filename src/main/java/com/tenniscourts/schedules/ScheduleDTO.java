package com.tenniscourts.schedules;

import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTO {

    private Long id;

    private TennisCourtDTO tennisCourt;

    @NotNull
    private Long tennisCourtId;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
    private LocalDateTime startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
    private LocalDateTime endDateTime;

}
