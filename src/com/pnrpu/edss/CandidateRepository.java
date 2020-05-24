package com.pnrpu.edss;

import java.util.Arrays;
import java.util.List;

public class CandidateRepository {
    public static final List<Candidate> candidates = Arrays.asList(
            new Candidate(1, "Степан"),
            new Candidate(2, "Рустам"),
            new Candidate(3, "Кирилл")
    );

    public static void printAllCandidates() {
        System.out.println("Список всех кандидатов:");
        candidates.forEach(System.out::println);
    }

    public static String getCandidatesInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (final Candidate candidate : candidates) {
            stringBuilder.append(candidate.getNameWithVotes()).append("\n");
        }
        return stringBuilder.toString();
    }

    public static Candidate getByCandidateId(final int id) {
        for (final Candidate candidate : candidates) {
            if (candidate.getId() == id) {
                return candidate;
            }
        }
        throw new RuntimeException("Кандидат не найден");
    }

    public static Candidate getCandidateWithMaxVotes() {
        Candidate candidateWithMaxVotes = candidates.iterator().next();
        for (final Candidate candidate : candidates) {
            if (candidate.getVotesCount() > candidateWithMaxVotes.getVotesCount()) {
                candidateWithMaxVotes = candidate;
            }
        }
        for (final Candidate candidate : candidates) {
            if (candidate.getId() != candidateWithMaxVotes.getId()
                    && candidate.getVotesCount()  ==  candidateWithMaxVotes.getVotesCount()) {
                throw new RuntimeException("Победитель не определен:\n" + getCandidatesInfo());
            }
        }
        return candidateWithMaxVotes;
    }
}
