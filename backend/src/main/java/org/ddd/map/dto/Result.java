package org.ddd.map.dto;

import java.util.List;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
public class Result {
    private List<Household> households;

    public List<Household> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<Household> households) {
        this.households = households;
    }
}
