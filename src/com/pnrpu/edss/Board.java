package com.pnrpu.edss;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CandidateRepository.printAllCandidates();
        System.out.println("Введите кол-во избирателей:");
        int votersCount = scanner.nextInt();
        for (int i = 1; i <= votersCount; i++) {
            System.out.println("Голосует " + i + " избиратель:");
            final List<Integer> processedId = new ArrayList<>(CandidateRepository.candidates.size());
            for (final Candidate candidate : CandidateRepository.candidates) {
                setRatingForCandidate(candidate, processedId);
            }
        }
        final Candidate winner = CandidateRepository.getCandidateWithMaxVotes();
        System.out.println("------------------------------");
        System.out.println("Пбедил кандидат №" + winner.getId() + ": " + winner.getName() + " (голосов: " + winner.getVotesCount() + ")\n");
        System.out.println("Результаты голосования:\n" + CandidateRepository.getCandidatesInfo());
    }

    public static void setRatingForCandidate(final Candidate candidate, final List<Integer> processedIds) {
        System.out.println(String.format("Укажите место для кандидата: %s от 1 до %s (занятые позиции: %s)",
                candidate.getName(), CandidateRepository.candidates.size(), processedIds));
        final int pos = scanner.nextInt();
        if (pos < 1 || pos > CandidateRepository.candidates.size() || processedIds.contains(pos)) {
            System.out.println("Вы выбрали некорректную позицию для кандидата");
            setRatingForCandidate(candidate, processedIds);
            return;
        }
        processedIds.add(pos);
        candidate.setVotesCount(candidate.getVotesCount() + CandidateRepository.candidates.size() - pos);
    }
}
