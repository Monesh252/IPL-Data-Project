package org.example;

import java.util.List;
import java.util.Map;

public interface ListOfScenarios {

    void matchesPlayedPerYear(List<String[]> matches);
    void matchesWonByAllTeams(List<String[]> matches);
    void etrasConcededPerYear(List<String[]> matches,List<String[]> deliveries, int year);
    void topEconomicalBowler2015(List<String[]> matches, List<String[]> deliveries, int year);
    void highestRunGetterInEachYear(List<String[]> matches, List<String[]> deliveries, int year);

}
