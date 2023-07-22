package com.umc.drawmap.repository;

import com.umc.drawmap.domain.Challenge;
import com.umc.drawmap.domain.User;
import com.umc.drawmap.domain.UserChallenge;
import com.umc.drawmap.domain.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface UserCourseRepository extends JpaRepository<UserCourse, Long>{
//    Optional<UserCourse> findUserCourseByUser(User user, UserCourse userCourse);

    Optional<UserCourse> findById(Long uCourseId);

    List<UserCourse> findAll();
    List<UserCourse> findAllByUser(User user);

    @Query("SELECT e FROM UserCourse e ORDER BY e.createdAt DESC")
    Page<UserCourse> findAllOrderByCreatedAtDesc(PageRequest pageRequest);

    @Query("SELECT e FROM UserCourse e order by e.scrapCount DESC ")
    Page<UserCourse> findAllOrderByScrapCountDesc(PageRequest pageRequest);
}