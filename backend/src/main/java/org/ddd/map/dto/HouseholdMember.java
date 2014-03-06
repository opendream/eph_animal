package org.ddd.map.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
public class HouseholdMember {

    /**
     * The type of member. Can be human or some type of animal.
     */
    private MemberType type;

    /**
     * The number of members of this type in the household.
     */
    private int count;

    private List<Symptom> symptoms = new ArrayList<>();

    public void addSymptom(Symptom symptom) {
        symptoms.add(symptom);
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

}
