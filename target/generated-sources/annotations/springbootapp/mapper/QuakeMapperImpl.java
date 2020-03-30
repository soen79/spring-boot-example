package springbootapp.mapper;

import javax.annotation.Generated;
import springbootapp.domain.EarthQuake;
import springbootapp.domain.EarthQuakeDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T21:03:03-0400",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_45 (Oracle Corporation)"
)
public class QuakeMapperImpl implements QuakeMapper {

    @Override
    public EarthQuakeDTO quakeToQuakeDTO(EarthQuake earthQuake) {
        if ( earthQuake == null ) {
            return null;
        }

        EarthQuakeDTO earthQuakeDTO = new EarthQuakeDTO();

        earthQuakeDTO.setQuakeMagnitude( earthQuake.getMagnitude() );
        earthQuakeDTO.setQuakeCity( earthQuake.getCity() );

        return earthQuakeDTO;
    }
}
