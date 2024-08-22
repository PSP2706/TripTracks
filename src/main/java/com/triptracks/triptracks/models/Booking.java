package com.triptracks.triptracks.models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings") // Optional: Specify the table name explicitly
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Explicit join column specification
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false) // Explicit join column specification
    private Train train;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "local_date", nullable = false)
    private LocalDate localDate;

    public Booking() {}

    public Booking(User user, Train train, int seatsBooked, LocalDate localDate) {
        this.user = user;
        this.train = train;
        this.seatsBooked = seatsBooked;
        this.localDate = localDate;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
