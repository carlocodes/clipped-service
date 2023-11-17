package com.carlocodes.clipped.mappers;

import com.carlocodes.clipped.dtos.BuddyDto;
import com.carlocodes.clipped.entities.Buddy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface BuddyMapper {
    BuddyMapper INSTANCE = Mappers.getMapper(BuddyMapper.class);

    @Mapping(source = "sender", target = "sender")
    @Mapping(source = "receiver", target = "receiver")
    BuddyDto mapToDto(Buddy buddy);

    Set<BuddyDto> mapToDtos(Set<Buddy> buddies);
}
