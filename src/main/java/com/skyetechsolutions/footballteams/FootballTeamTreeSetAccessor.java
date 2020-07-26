package com.skyetechsolutions.footballteams;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class FootballTeamTreeSetAccessor {
    public static FootballTeam getByName(String name){
        TreeSet<FootballTeam> result = FootballTeamDataArray.getInstance().footballTeams
                .stream()
                .filter(p -> p.getName() == name)
                .collect(Collectors.toCollection(TreeSet::new));

        return result.isEmpty() ? null : result.first();

    }

//    public FootballTeamTreeSet<FootballTeam> sortBy
}
