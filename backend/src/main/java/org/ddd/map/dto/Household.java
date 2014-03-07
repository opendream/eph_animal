package org.ddd.map.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
public class Household {
    private String id;
    /**
     * latitude
     */
    private double lat;
    /**
     * longtitude
     */
    private double lng;

    private Address address;

    private HouseholdMember infectedMember;

    private List<HouseholdMember> members = new ArrayList<>();


    public void addMember(HouseholdMember member) {
        members.add(member);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<HouseholdMember> getMembers() {
        return members;
    }

    public void setMembers(List<HouseholdMember> members) {
        this.members = members;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HouseholdMember getInfectedMember() {
        return infectedMember;
    }

    public void setInfectedMember(HouseholdMember infectedMember) {
        this.infectedMember = infectedMember;
    }
}
