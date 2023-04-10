package com.zadaca.events.repository;

import com.zadaca.events.domains.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
