package springbootapp;

import org.junit.Test;
import springbootapp.domain.EarthQuake;
import springbootapp.domain.EarthQuakeDTO;
import springbootapp.mapper.QuakeMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class QuackMapperTest {

    @Test
    public void testMapper() {
        EarthQuake q = new EarthQuake();
        q.setCity("Montreal");
        q.setMagnitude(5);

        EarthQuakeDTO dto = QuakeMapper.INSTANCE.quakeToQuakeDTO(q);

        assertThat(dto.getQuakeCity().equals(q.getCity()));
    }
}
