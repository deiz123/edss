package com.pnrpu.edss.condorcet;

import com.pnrpu.edss.Candidate;
import com.pnrpu.edss.CandidateRepository;

import java.util.Scanner;

public class ClearWinner {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CandidateRepository.printAllCandidates();
        System.out.println("Введите кол-во избирателей:");
        int votersCount = scanner.nextInt();
        for (int i = 1; i <= votersCount; i++) {
            System.out.println("Голосует " + i + " избиратель:");
            for (int j = 1; j <= CandidateRepository.candidates.size(); j++) {
                final Candidate firstCandidate = CandidateRepository.getByCandidateId(j);
                for (int k = j + 1; k <= CandidateRepository.candidates.size(); k++) {
                    final Candidate secondCandidate = CandidateRepository.getByCandidateId(k);
                    comparePairOfCandidates(firstCandidate, secondCandidate);
                }
            }
        }
        final Candidate winner = CandidateRepository.getCandidateWithMaxVotes();
        System.out.println("------------------------------");
        System.out.println("Пбедил кандидат №" + winner.getId() + ": " + winner.getName() + " (голосов: " + winner.getVotesCount() + ")\n");
        System.out.println("Результаты голосования:\n" + CandidateRepository.getCandidatesInfo());
    }

    public static void comparePairOfCandidates(final Candidate firstCandidate, final Candidate secondCandidate) {
        System.out.println("Выберете одного из двух кандидатов:");
        System.out.println(firstCandidate);
        System.out.println(secondCandidate);
        final int res = scanner.nextInt();
        if (res != firstCandidate.getId() && res != secondCandidate.getId()) {
            System.out.println("Вы ввели некорректный номер кандидата!!!");
            comparePairOfCandidates(firstCandidate, secondCandidate);
            return;
        }
        if (res == firstCandidate.getId()) {
            firstCandidate.addVote();
        } else if (res == secondCandidate.getId()) {
            secondCandidate.addVote();
        }
    }
}
