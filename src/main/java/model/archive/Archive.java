package model.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Contains the informations to be saved in the database
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Useage")
public class Archive
{
    /**
     * Identifyer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contains the type of the interractin: prime test, prime generate, euclides, modpower or encryption.
     */
    @Column
    private String type;

    /**
     * Contains the ending time of the calculation.
     */
    @Column
    private String time;

    /**
     * Converts the information in the {@link Archive} object to a string.
     * @return The created string
     */
    public String toString()
    {
        String ret = new String("Id:" + id + " Type:" + type + " time:" + time);
        return ret;
    }
}
