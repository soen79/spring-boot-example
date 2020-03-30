package springbootapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import springbootapp.domain.EarthQuake;
import springbootapp.domain.EarthQuakeDTO;

@Mapper
public interface QuakeMapper {

    QuakeMapper INSTANCE  = Mappers.getMapper(QuakeMapper.class);

    @Mappings({
            @Mapping(source = "city", target ="quakeCity"),
            @Mapping(source = "magnitude", target ="quakeMagnitude")
    })

    EarthQuakeDTO quakeToQuakeDTO(EarthQuake earthQuake);
}
