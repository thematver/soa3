package xyz.anomatver.soa3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import xyz.anomatver.soa3.helper.LocalDateTimeAdapter;

import java.time.LocalDateTime;

public class MusicBand {

    private Long id;

    private String name;

    private Coordinates coordinates;

    private int numberOfParticipants;

    private String genre;

    private Studio studio;

    private boolean nominatedToGrammy;

    public MusicBand(Long id, String name, Coordinates coordinates, int numberOfParticipants, String genre, Studio studio, boolean nominatedToGrammy) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.studio = studio;
        this.nominatedToGrammy = nominatedToGrammy;
    }

    public MusicBand() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }




    public int getNumberOfParticipants() {
        return this.numberOfParticipants;
    }

    public String getGenre() {
        return this.genre;
    }

    public Studio getStudio() {
        return this.studio;
    }

    public boolean isNominatedToGrammy() {
        return this.nominatedToGrammy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public void setNominatedToGrammy(boolean nominatedToGrammy) {
        this.nominatedToGrammy = nominatedToGrammy;
    }
}