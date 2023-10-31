package tech.foxlo.bookingservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.foxlo.bookingservice.entity.Station;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s.name from Station s")
     List<String> findAllNames();
    Optional<Station> findStationByNameIsContaining(String name);
     Optional<Station> findStationByName(String name);
}
