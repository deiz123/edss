package com.pnrpu.edss.condorcet;

import com.pnrpu.edss.Candidate;
import com.pnrpu.edss.CandidateRepository;

import java.util.Arrays;
import java.util.Scanner;

public class Simpson {
    private static final Scanner scanner = new Scanner(System.in);

    private static final int[][] candidatesVotes = new int[CandidateRepository.candidates.size()][CandidateRepository.candidates.size()];

    static {
        Arrays.stream(candidatesVotes).forEach(candidateVotes -> Arrays.fill(candidateVotes, 0));
    }

    public static void main(String[] args) {
        CandidateRepository.printAllCandidates();
        System.out.println("Введите кол-во избирателей:");
        int votersCount = scanner.nextInt();
        for (int i = 1; i <= CandidateRepository.candidates.size(); i++) {
            System.out.println("Голосует " + i + " избиратель:");
            for (int j = 1; j <= CandidateRepository.candidates.size(); j++) {
                final Candidate firstCandidate = CandidateRepository.getByCandidateId(j);
                for (int k = j + 1; k <= votersCount; k++) {
                    final Candidate secondCandidate = CandidateRepository.getByCandidateId(k);
                    comparePairOfCandidates(firstCandidate, secondCandidate);
                }
            }
        }

        System.out.println("------------------------------");
        System.out.println("Матрица голосования");
        for (int[] candidatesVote : candidatesVotes) {
            for (int j : candidatesVote) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < candidatesVotes.length; i++) {
            int minInIteration = candidatesVotes[i][i == 0 ? 1 : 0];
            for (int i1 = 0; i1 < candidatesVotes[i].length; i1++) {
                if (i == i1) {
                    continue;
                }
                minInIteration = Math.min(candidatesVotes[i][i1], minInIteration);
            }
            CandidateRepository.getByCandidateId(i + 1).setVotesCount(minInIteration);
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
            candidatesVotes[firstCandidate.getId() - 1][secondCandidate.getId() - 1]++;
        } else {
            candidatesVotes[secondCandidate.getId() - 1][firstCandidate.getId() - 1]++;
        }
    }
}
