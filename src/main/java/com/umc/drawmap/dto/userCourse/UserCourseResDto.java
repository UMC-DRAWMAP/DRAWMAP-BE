package com.umc.drawmap.dto.userCourse;

import com.umc.drawmap.domain.User;
import com.umc.drawmap.dto.UserResDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserCourseResDto {


    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserCourseDto{
        private Long userCourseId;

        private Boolean isScraped;

        private String title;
        private LocalDateTime createdDate;
        //        private String path; // 코스 경로
        private String difficulty;
        private String content;

        private String area;

        private String image;
        private int scrapCount;

        private UserResDto.UserDto user; // userId, profileImg, name

    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserCourseListDto{
        private List<UserCourseResDto.UserCourseDto> userCourseList;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyUserCourseDto{
        private Long userCourseId;

        private String area;
        private LocalDateTime createdDate;

        private String image;

        private UserResDto.UserDto user; // userId, profileImg, name

    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyUserCourseListDto{
        private List<UserCourseResDto.MyUserCourseDto> UserCourseList;
    }
}
