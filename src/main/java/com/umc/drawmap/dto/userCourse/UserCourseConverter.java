package com.umc.drawmap.dto.userCourse;

import com.umc.drawmap.domain.UserCourse;
import com.umc.drawmap.domain.User;
import com.umc.drawmap.dto.UserResDto;
import com.umc.drawmap.repository.UserRepository;
import com.umc.drawmap.service.ScrapService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserCourseConverter {

    private static UserRepository userRepository;
    private static ScrapService scrapService;

    public static UserCourseResDto.UserCourseDto toUserCourseDto(UserCourse userCourse){

        User user = userRepository.findUserByUserCourse(userCourse);
        Boolean isScraped = scrapService.findScrapByUserAndUserCourse(user, userCourse);

        return UserCourseResDto.UserCourseDto.builder()
                .title(userCourse.getUserCourseTitle())
                .userCourseId(userCourse.getId())
                .content(userCourse.getUserCourseContent())
                .area(userCourse.getUserCourseArea())
                .image(userCourse.getUserImage())
                .createdDate(userCourse.getCreatedAt())
                .difficulty(userCourse.getUserCourseDifficulty())
                .isScraped(isScraped)
                .user(UserResDto.UserDto.builder().userId(user.getId()).profileImg(user.getProfileImg()).nickName(user.getNickName()).build())
                .scrapCount(userCourse.getScrapCount())
                .build();
    }

    public static List<UserCourseResDto.UserCourseDto> toUserCourseDtoList(List<UserCourse> userCourseList){
        return userCourseList.stream()
                .map(userCourse -> toUserCourseDto(userCourse))
                .collect(Collectors.toList());
    }

    public static UserCourseResDto.MyUserCourseDto toMyUserCourseDto(UserCourse userCourse){
        User user = userRepository.findUserByUserCourse(userCourse);

        return UserCourseResDto.MyUserCourseDto.builder()
                .userCourseId(userCourse.getId())
                .area(userCourse.getUserCourseArea())
                .image(userCourse.getUserImage())
                .createdDate(userCourse.getCreatedAt())
                .user(UserResDto.UserDto.builder().userId(user.getId()).profileImg(user.getProfileImg()).nickName(user.getNickName()).build())
                .build();
    }

    public static List<UserCourseResDto.MyUserCourseDto> toUserCourseDtoMyList(List<UserCourse> userCourseList){
        return userCourseList.stream()
                .map(userCourse -> toMyUserCourseDto(userCourse))
                .collect(Collectors.toList());
    }

}