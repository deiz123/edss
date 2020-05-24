package com.pnrpu.edss;

import java.util.Scanner;

public class RelativeMajority {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CandidateRepository.printAllCandidates();
        System.out.println("Введите кол-во избирателей:");
        int votersCount = scanner.nextInt();
        for (int i = 1; i <= votersCount; i++) {
            System.out.println("Голосует " + i + " избиратель (выберете № кандидата):");
            int candidateId = scanner.nextInt();
            CandidateRepository.getByCandidateId(candidateId).addVote();
        }

        final Candidate winner = CandidateRepository.getCandidateWithMaxVotes();
        System.out.println("------------------------------");
        System.out.println("Пбедил кандидат №" + winner.getId() + ": " + winner.getName() + " (голосов: " + winner.getVotesCount() + ")\n");
        System.out.println("Результаты голосования:\n" + CandidateRepository.getCandidatesInfo());
    }
}
