package com.pnrpu.edss;

import java.util.Objects;

/**
 * @author kirill.nivin
 * Created: 19/05/2020
 */
public class Candidate {
    private int id;
    private String name;
    private int votesCount = 0;

    public Candidate(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(final int votesCount) {
        this.votesCount = votesCount;
    }

    public void addVote() {
        votesCount = votesCount + 1;
    }

    public void removeVote() {
        votesCount = votesCount - 1;
    }

    public String getNameWithVotes() {
        return name + " (Голосов: " + votesCount + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                votesCount == candidate.votesCount &&
                Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, votesCount);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
