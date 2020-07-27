package com.skyetechsolutions.footballteams;

import java.util.TreeSet;

public class FootballTeamTreeSet<E> extends TreeSet<E> {
    @Override
    public boolean contains(Object o) {
        return containsName((FootballTeamTreeSet<FootballTeam>)this, ((FootballTeam) o).getName());
    }

    private boolean containsName(final FootballTeamTreeSet<FootballTeam> set, final String teamName){
        return set.stream().anyMatch(o -> o.getName().equals(teamName));
    }

    @Override
    public boolean add(Object o){
        FootballTeam new_FootballTeam = (FootballTeam) o;
        if (!this.contains(new_FootballTeam)) {
            super.add((E) o);
            return true;
        } else {
            return false;
        }
    }
}
