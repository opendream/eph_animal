package org.ddd.map.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ddd.map.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Mark Hofmann (mark@mark-hofmann.de)
 */
@Controller
public class ApiController {
    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam String callback, @RequestParam(required = false) Integer dangerLevel, @RequestParam(required = false) SymptomType symptomType) {
        final ArrayList<Household> households = new ArrayList<>();
        if (dangerLevel == null) {
            dangerLevel = 0;
        }
        for (int i = 0; i < 100; i++) {
            final Household household = getHousehold(dangerLevel, symptomType);
            if (household != null) {
                final List<HouseholdMember> members = household.getMembers();
                int highestDangerLevel = 0;
                for (HouseholdMember member : members) {
                    final List<Symptom> symptoms = member.getSymptoms();
                    for (Symptom symptom : symptoms) {
                        final int dangerLevel1 = symptom.getDangerLevel();
                        if (dangerLevel1 > highestDangerLevel) {
                            highestDangerLevel = dangerLevel1;
                            household.setInfectedMember(member);
                        }
                    }
                }
                households.add(household);
            }
        }
//        Result result = new Result();
//        result.setHouseholds(households);
        String result;
        try {
            result = mapper.writeValueAsString(households);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = null;
        }
        result = callback + "(" + result + ")";
        return result;
    }

    private double getRandomDouble(double rangeMin, double rangeMax) {
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

    private int getRandomInt(int rangeMin, int rangeMax) {
        Random r = new Random();
        return rangeMin + r.nextInt(rangeMax + 1 - rangeMin);
    }

    private Household getHousehold(int dangerLevel, SymptomType symptomType) {
        final Household household = new Household();
        household.setId(UUID.randomUUID().toString());
        Address address = new Address();
        address.setEmail("some@email.com");
        address.setFirstName("Dang");
        address.setLastName("Haeng");
        address.setStreet("15, Huay Kaew");
        address.setPhone("+66 3453 3742");
        address.setPostCode("34535");
        address.setCity("Not Chiang Mai");
        address.setCountryCode("TH");
        household.setAddress(address);
        household.setLat(getRandomDouble(14.8475699d, 19.5415012d));
        household.setLng(getRandomDouble(98.1163605d, 105.6423037d));
        addMember(household, MemberType.PET, getRandomInt(0, 4), dangerLevel, symptomType);
        addMember(household, MemberType.BIRD, getRandomInt(0, 20), dangerLevel, symptomType);
        addMember(household, MemberType.LIVESTOCK, getRandomInt(0, 10), dangerLevel, symptomType);
        addMember(household, MemberType.EXOTIC, getRandomInt(0, 4), dangerLevel, symptomType);
        addMember(household, MemberType.HUMAN, getRandomInt(1, 6), dangerLevel, symptomType);
        final List<HouseholdMember> members = household.getMembers();
        boolean hasSymptoms = false;
        for (HouseholdMember member : members) {
            if (member.getSymptoms().size() > 0) {
                hasSymptoms = true;
                break;
            }
        }
        return hasSymptoms ? household : null;
    }

    private void addMember(Household household, MemberType type, int totalCount, int dangerLevel, SymptomType symptomType) {
        if (totalCount > 0) {
            final HouseholdMember member = getMember(type, totalCount, dangerLevel, symptomType);
            household.addMember(member);
        }
    }

    private HouseholdMember getMember(MemberType type, int totalCount, int selectedDangerLevel, SymptomType selectedSymptomType) {
        final HouseholdMember member = new HouseholdMember();
        member.setCount(totalCount);
        member.setType(type);
        if (totalCount > 0) {
            int sickCount = 0;
            final SymptomType[] symptomTypes = SymptomType.values();
            Random random = new Random();
            for (SymptomType symptomType : symptomTypes) {
                final int chance = random.nextInt(100);
                System.out.println("chance:" + chance);
                if (chance > 60) {
                    final int maxSickCount = totalCount - sickCount;
                    System.out.println("maxSickCount:" + maxSickCount);
                    if (maxSickCount < 1) {
                        break;
                    }
                    int count = getRandomInt(0, maxSickCount) + 1;
                    if (count > maxSickCount) {
                        count = maxSickCount;
                    }
                    // ensure that sickCount is not > totalCount
                    if (sickCount + count < totalCount) {
                        if (selectedSymptomType == null || symptomType == selectedSymptomType) {
                            final Symptom symptom = getSymptom(symptomType, count);
                            if (symptom.getDangerLevel() >= selectedDangerLevel) {
                                member.addSymptom(symptom);
                                sickCount += count;
                            }
                        }
                    } else {
                        break;
                    }
                }
                if (totalCount - sickCount == 0) {
                    break;
                }
            }
        }
        return member;
    }

    private Symptom getSymptom(SymptomType type, int count) {
        final Symptom symptom = new Symptom();
        symptom.setDangerLevel(getRandomInt(1, 5));
        symptom.setCount(count);
        symptom.setType(type);
        symptom.addResourceLink("http://opendream.org/data?id=" + getRandomInt(1, 1000000));
        return symptom;
    }
}
