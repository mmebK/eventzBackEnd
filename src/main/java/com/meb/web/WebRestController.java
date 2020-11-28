package com.meb.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dao.IEventRepo;
import com.meb.domain.Response;
import com.meb.entity.Event;
import com.meb.entity.EventSession;
import com.meb.service.IEventSessionService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


@RestController
public class WebRestController {

    final IEventRepo eventRepo;
    final IEventSessionService sessionService;

    @Autowired
    ServletContext context;

    @Autowired
    public WebRestController(IEventRepo eventRepo, IEventSessionService sessionService) {
        this.eventRepo = eventRepo;
        this.sessionService = sessionService;
    }


    @CrossOrigin
    @PostMapping("/saveEvent")
    public ResponseEntity<Response> saveEvent(@RequestParam("file") MultipartFile file, @RequestParam("event") String event) throws IOException {

        //Event event=new ObjectM
        Event event1 = new ObjectMapper().readValue(event, Event.class);
        boolean isExist = new File(context.getRealPath("/userprofile")).exists();
        if (!isExist) {
            new File(context.getRealPath("/userprofile")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
        //File serverFile = new File(context.getRealPath("/userprofile/" + File.separator + modifiedFileName));

        try {
            FileUtils.writeByteArrayToFile(new File(System.getProperty("user.home") + "/eventsPhotos/photos/" + File.separator + modifiedFileName), file.getBytes());
            // Files.write(Paths.get(System.getProperty("user.home") + "/eventsPhotos/photos/"), file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

        event1.setFileName(modifiedFileName);

        Event dbEvent = eventRepo.save(event1);
        if (dbEvent != null) {
            return new ResponseEntity<Response>(new Response("user is saved"), HttpStatus.OK);

        } else {
            return new ResponseEntity<Response>(new Response("user is not saved"), HttpStatus.BAD_REQUEST);
        }


    }

    @CrossOrigin
    @GetMapping(path = "/photosProduct/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhotoByName(@PathVariable("fileName") String fileName) throws Exception {
        System.out.println(fileName);
        Event event = eventRepo.findByFileName(fileName);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/eventsPhotos/photos/" + event.getFileName()));
    }

    @CrossOrigin
    @GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhotoById(@PathVariable("id") Long id) throws Exception {
        Event event = eventRepo.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/eventsPhotos/photos/" + event.getFileName()));
    }

    @PostMapping("/session")
    public void addSession(@RequestParam(value = "event_id") Long id, @RequestBody EventSession session) {


        /**
         *  both of the solutions work
         *
         *
         */
        Optional<Event> result = eventRepo.findById(id);

        if (result.isPresent()) {
            Event event = result.get();
            event.addSession(session);
            eventRepo.save(event);
        } else
            throw new RuntimeException("Event of id " + id + " is not found");
        /*Event event = eventRepo.findEventById(id);
        event.addSession(session);
        eventRepo.save(event);*/

    }


    @DeleteMapping("/session")
    public void deleteSession(@RequestParam(value = "id") Long id) {
        sessionService.deleteEventSessionById(id);
    }

    @PutMapping("/session")
    public void updateSession(@RequestParam(value = "id") Long id, @RequestBody EventSession session) {
        session.setId(id);
        sessionService.saveEventSession(session);
    }
}
