package org.ddd.map.dto;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
public class Symptom {
    private SymptomType type;
    private int count;

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
}
