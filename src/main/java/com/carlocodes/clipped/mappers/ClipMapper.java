package com.carlocodes.clipped.mappers;

import com.carlocodes.clipped.dtos.ClipDto;
import com.carlocodes.clipped.entities.Clip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClipMapper {
    ClipMapper INSTANCE = Mappers.getMapper(ClipMapper.class);

    ClipDto mapToDto(Clip clip);
    List<ClipDto> mapToDtos(List<Clip> clips);
}
