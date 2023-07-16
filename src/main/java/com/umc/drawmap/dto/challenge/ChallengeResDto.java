package com.umc.drawmap.dto.challenge;

import com.umc.drawmap.domain.User;
import com.umc.drawmap.dto.UserResDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ChallengeResDto {


    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ChallengeDto{
        private Long challengeId;

        private Boolean isScraped; // 현재 유저가 스크랩을 눌렀는지 여부 (하트 색칠 여부)

        private String title;
        private String path; // 코스 경로
        private String difficulty;
        private String area;

        private LocalDateTime createdDate;

        private String image;
        private int scrapCount;

        private String content;

        private UserResDto.UserDto user; // userId, profileImg, name

    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ChallengeListDto{
        private List<ChallengeResDto.ChallengeDto> challengeList;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyChallengeDto{
        private Long challengeId;

        private String area;
        private LocalDateTime createdDate;

        private String image;

        private UserResDto.UserDto user; // userId, profileImg, name

    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyChallengeListDto{
        private List<ChallengeResDto.MyChallengeDto> ChallengeList;
    }





}
