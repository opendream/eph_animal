package org.ddd.map.api;

import org.ddd.map.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Household> get() {
        final ArrayList<Household> households = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            households.add(getHousehold());
        }
        return households;
    }

    private double getRandomDouble(double rangeMin, double rangeMax) {
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

    private int getRandomInt(int rangeMin, int rangeMax) {
        Random r = new Random();
        return rangeMin + r.nextInt(rangeMax - rangeMin);
    }

    private Household getHousehold() {
        final Household household = new Household();
        household.setId(UUID.randomUUID().toString());
        household.setLat(getRandomDouble(14.8475699d, 19.5415012d));
        household.setLng(getRandomDouble(98.1163605d, 105.6423037d));
        addMember(household, MemberType.HUMAN, getRandomInt(1, 6));
        addMember(household, MemberType.PET, getRandomInt(0, 4));
        addMember(household, MemberType.BIRD, getRandomInt(0, 20));
        addMember(household, MemberType.FARM, getRandomInt(0, 10));
        addMember(household, MemberType.EXOTIC, getRandomInt(0, 4));
        return household;
    }

    private void addMember(Household household, MemberType type, int totalCount) {
        if (totalCount > 0) {
            household.addMember(getMember(type, totalCount));
        }
    }

    private HouseholdMember getMember(MemberType type, int totalCount) {
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
                        member.addSymptom(getSymptom(symptomType, count));
                        sickCount += count;
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
        symptom.setCount(count);
        symptom.setType(type);
        return symptom;
    }
}
