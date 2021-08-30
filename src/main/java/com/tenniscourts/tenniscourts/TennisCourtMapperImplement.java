package com.tenniscourts.tenniscourts;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public class TennisCourtMapperImplement implements TennisCourtMapper{

	@Override
	public TennisCourtDTO map(TennisCourt source) {
		TennisCourtDTO dto = new TennisCourtDTO();
		dto.setId(source.getId());
		dto.setName(source.getName());
		dto.setTennisCourtSchedules(null);
		return dto;
	}

	@Override
	public TennisCourt map(TennisCourtDTO source) {
		TennisCourt court = new TennisCourt();
		court.setId(source.getId());
		court.setName(source.getName());
		return court;
	}

}
