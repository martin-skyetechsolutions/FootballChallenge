package com.skyetechsolutions.footballteams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FootballTeamTreeSetAccessor {
    public static FootballTeam getByName(String name){
        TreeSet<FootballTeam> result = FootballTeamDataArray.getInstance().footballTeams
                .stream()
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toCollection(TreeSet::new));

        return result.isEmpty() ? null : result.first();

    }
    public static FootballTeamTreeSet<FootballTeam> getAllTeams(){
        return FootballTeamDataArray.getInstance().footballTeams;
    }

    public static ArrayList<FootballTeam> getAllTeamsSortByCapacity(){
        ArrayList<FootballTeam> result = FootballTeamDataArray.getInstance().footballTeams
                .stream()
                .sorted(Comparator.comparingInt(FootballTeam::getStadiumCapacity)
                    .reversed()
                .thenComparing(FootballTeam::getName))
                .collect(Collectors.toCollection(ArrayList<FootballTeam>::new));
        return result;
    }
}
