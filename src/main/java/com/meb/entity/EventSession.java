package com.meb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
public class EventSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private long id;

    @Column(name = "name")
    String name;

    @Column(name = "presenter")
    String presenter;


    @Column(name = "level")
    String level;

    @Column(name = "description")
    String description;


    /**
     * to be added: Date and Time Sections
     */

    //to add voter later

    @ManyToOne(cascade = {

            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @Override
    public String toString() {
        return "EventSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", presenter='" + presenter + '\'' +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// avant impo
//44203
//60000
// apres impo
//9900 coti
//39600 imposable  1715

//5*2500
//39000 => coti:6435 impo 2.2%:858
