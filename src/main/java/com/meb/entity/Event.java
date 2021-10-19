package com.meb.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private long id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "online_Url")
    private String onlineUrl;

    @Column(name = "image")
    private Byte[] image;

    @Column(name = "file_name")
    private String fileName;


    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // @JsonFormat(pattern = "L,dd yyyy")
    @Column(name = "start_date")
    String startOfEvent;

    @Column(name = "end_date")
    String endOfEvent;

    // @JsonFormat(pattern = "hh:mm")
    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    String startTime;

    // @JsonFormat(pattern = "hh:mm")
    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    String endTime;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    @JsonManagedReference
    private List<EventSession> eventSessions;


    public void addSession(EventSession session) {
        this.eventSessions.add(session);
        session.setEvent(this);
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", onlineUrl='" + onlineUrl + '\'' +
                ", startDate=" + startOfEvent +
                ", endOfEvent=" + endOfEvent +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


