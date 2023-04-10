package com.zadaca.events.repository;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Event;
import com.zadaca.events.domains.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.date >= CURDATE()")
    List<Event> findByDateAfterCurrentDate();

    List<Event> findEventsByName(String name);
    List<Event> findEventsByLocation(Location location);
    List<Event> findEventsByCategory(Category category);
    List<Event> findEventsByCategoryAndLocation(Category category, Location location);
    List<Event> findEventsByCategoryAndName(Category category, String name);
    List<Event> findEventsByLocationAndName(Location location, String name);
    List<Event> findEventsByNameAndLocationAndCategory(String name, Location location, Category category);
}
