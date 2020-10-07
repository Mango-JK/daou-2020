package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.repository.ProblemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemsService {

    private final ProblemsRepository problemsRepository;

    public Problems saveProblems(Problems problems) {
        return problemsRepository.save(problems);
    }

    public Problems findByProblemId(int problemId) {
        return problemsRepository.findByProblemId(problemId);
    }

    public void deleteProblem(int problemId) {
        Problems targetProblem = findByProblemId(problemId);
        problemsRepository.delete(targetProblem);
    }

}
