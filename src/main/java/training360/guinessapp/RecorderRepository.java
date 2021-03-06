package training360.guinessapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.guinessapp.dto.RecorderShortDto;

import java.util.List;

public interface RecorderRepository extends JpaRepository<Recorder, Long> {

    @Query("select new training360.guinessapp.dto.RecorderShortDto(r.name, r.dateOfBirth)" +
            " from Recorder r " +
            "where substring(r.name,1) = 'B' or r.name like '%e%' " +
            "order by r.dateOfBirth desc ")
    List<RecorderShortDto> getFilteredRecorders();
}
