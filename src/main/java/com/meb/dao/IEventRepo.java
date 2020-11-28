
package com.meb.dao;

import com.meb.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@RepositoryRestResource(path = "eventz")
@CrossOrigin
public interface IEventRepo extends JpaRepository<Event, Long> {

    Event findEventById(long id);

    Event findByFileName(String image);

    List<Event> findByNameContaining(@RequestParam("name") String name);

    List<Event> findByCategoryContaining(@RequestParam("category") String category);


    List<Event> findByNameContainingAndLocation_CityContainingAndCategoryContaining(@RequestParam("name") String name, @RequestParam("location") String location, @RequestParam("category") String category);

    List<Event> findByLocation_CountryContainingOrLocation_CityContaining(@RequestParam("country") String country, @RequestParam("city") String city);

    List<Event> findByLocation_CountryContainingAndLocation_CityContaining(@RequestParam("country") String country, @RequestParam("city") String city);

    List<Event> findByLocation_CityContaining(@RequestParam("city") String city);

    List<Event> findByLocation_CountryContaining(@RequestParam("country") String country);


    /*    List<ITEvent> findAllITEvent();
    ITEvent findITEventById(int id);
    void saveITEvent(ITEvent itEvent);
    void deleteITEventById(int id);*/
}

