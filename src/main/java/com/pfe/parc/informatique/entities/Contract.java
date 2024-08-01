package com.pfe.parc.informatique.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(nullable = false, length = 255)
    private String subject;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    // Use BLOB for storing file data
    @Lob // Use @Lob for large objects like files
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData; // Change from String to byte[] to store binary data

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Transient
    @JsonInclude
    private String period;

    // Automatically calculate the contract period based on start and end dates
    private void updatePeriod() {
        if (this.startDate != null && this.endDate != null) {
            long diffInMillies = Math.abs(this.endDate.getTime() - this.startDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long years = diff / 365;
            long months = (diff % 365) / 30;
            long days = (diff % 365) % 30;
            this.period = years + " years, " + months + " months, " + days + " days";
        } else {
            this.period = "";
        }
    }

    // Override set methods to update period
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        updatePeriod();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        updatePeriod();
    }
}
