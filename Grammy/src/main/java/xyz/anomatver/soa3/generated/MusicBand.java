//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.02.14 at 08:02:26 PM MSK 
//


package xyz.anomatver.soa3.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for MusicBand complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MusicBand"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordinates" type="{http://anomatver.xyz/soa3}Coordinates"/&gt;
 *         &lt;element name="numberOfParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="studio" type="{http://anomatver.xyz/soa3}Studio"/&gt;
 *         &lt;element name="nominatedToGrammy" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MusicBand", propOrder = {
    "id",
    "name",
    "coordinates",
    "numberOfParticipants",
    "studio",
    "nominatedToGrammy",
        "genre"
})
public class MusicBand {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Coordinates coordinates;
    protected int numberOfParticipants;
    @XmlElement(required = true)
    protected Studio studio;
    protected boolean nominatedToGrammy;

    @XmlElement(required = true)
    protected String genre;
    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    public void setGenre(String genre) { this.genre = genre; }
    public String getGenre() { return genre; }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link Coordinates }
     *     
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coordinates }
     *     
     */
    public void setCoordinates(Coordinates value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the numberOfParticipants property.
     * 
     */
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Sets the value of the numberOfParticipants property.
     * 
     */
    public void setNumberOfParticipants(int value) {
        this.numberOfParticipants = value;
    }

    /**
     * Gets the value of the studio property.
     * 
     * @return
     *     possible object is
     *     {@link Studio }
     *     
     */
    public Studio getStudio() {
        return studio;
    }

    /**
     * Sets the value of the studio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Studio }
     *     
     */
    public void setStudio(Studio value) {
        this.studio = value;
    }

    /**
     * Gets the value of the nominatedToGrammy property.
     * 
     */
    public boolean isNominatedToGrammy() {
        return nominatedToGrammy;
    }

    /**
     * Sets the value of the nominatedToGrammy property.
     * 
     */
    public void setNominatedToGrammy(boolean value) {
        this.nominatedToGrammy = value;
    }

}
