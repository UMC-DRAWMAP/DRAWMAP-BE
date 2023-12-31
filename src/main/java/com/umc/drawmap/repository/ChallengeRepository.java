package com.umc.drawmap.repository;

import com.umc.drawmap.domain.Challenge;
import com.umc.drawmap.domain.UserChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>{

    Optional<Challenge> findById(Long challengeId);

    Challenge findByUserChallenge(UserChallenge userChallenge);

    List<Challenge> findAllBySidoOrSgg(String sido, String sgg);

    List<Challenge> findAllByOrderByScrapCountDesc();

    List<Challenge> findAllByOrderByCreatedAtDesc();
}
