package com.skyetechsolutions.footballteams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FootballTeamTreeSetAccessor {

    private static FootballTeamTreeSet<FootballTeam> dataArraylayer = FootballTeamDataArray.getInstance().footballTeams;

    public static int size(){
        return  dataArraylayer.size();
    }

    public static boolean add(FootballTeam input){
        return dataArraylayer.add(input);
    }

    public static FootballTeam getByName(String name){
        TreeSet<FootballTeam> result = dataArraylayer
                .stream()
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toCollection(TreeSet::new));

        return result.isEmpty() ? null : result.first();
    }

    public static FootballTeamTreeSet<FootballTeam> getAllTeams(){
        return dataArraylayer;
    }

    public static ArrayList<FootballTeam> getAllTeamsSortByCapacity(){
        ArrayList<FootballTeam> result = dataArraylayer
                .stream()
                .sorted(Comparator.comparingInt(FootballTeam::getStadiumCapacity)
                    .reversed()
                .thenComparing(FootballTeam::getName))
                .collect(Collectors.toCollection(ArrayList<FootballTeam>::new));
        return result;
    }
}
