package org.ddd.map.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
public class Symptom {
    private SymptomType type;
    private int count;
    /**
     * The danger level from 0 (none) to 5 (high danger).
     */
    private int dangerLevel;

    /**
     * Text links (plain text) or urls where the original data was retrieved from.
     */
    private List<String> resourceLinks = new ArrayList<>();

    public SymptomType getType() {
        return type;
    }

    public void setType(SymptomType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public void addResourceLink(String link) {
        resourceLinks.add(link);
    }

    public void setResourceLinks(List<String> resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    public List<String> getResourceLinks() {
        return resourceLinks;
    }
}
