package com.triptracks.triptracks.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;
    
    @Column(nullable = false)
    private int availableSeats;

    @ManyToOne(fetch = FetchType.LAZY) // Assuming each train is added by one user
    @JoinColumn(name = "added_by") // The column in the `trains` table that will store the reference to the `User`
    private User addedBy;

    public Train() {}

    public Train(Long id, String name, String source, String destination, int availableSeats, User addedBy) {
        super();
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.addedBy = addedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
