package training360.guinessapp;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "world_record")
public class WorldRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "wr_value")
    private Double value;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "date_of_record")
    private LocalDate dateOfRecord;

    @ManyToOne
    private Recorder recorder;

    public WorldRecord(String description, Double value, String unitOfMeasure, LocalDate dateOfRecord, Recorder recorder) {
        this.description = description;
        this.value = value;
        this.unitOfMeasure = unitOfMeasure;
        this.dateOfRecord = dateOfRecord;
        this.recorder = recorder;
    }
}
