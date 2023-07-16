package com.umc.drawmap.dto.challenge;

import com.umc.drawmap.domain.Challenge;
import com.umc.drawmap.domain.User;
import com.umc.drawmap.domain.UserChallenge;
import com.umc.drawmap.dto.UserResDto;
import com.umc.drawmap.repository.UserChallengeRepository;
import com.umc.drawmap.repository.UserRepository;
import com.umc.drawmap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChallengeConverter {

    private static UserChallengeRepository userChallengeRepository;
    private static UserRepository userRepository;
    private static ScrapService scrapService;

    public static ChallengeResDto.ChallengeDto toChallengeDto(Challenge challenge){

        UserChallenge userChallenge = userChallengeRepository.findUserChallengeByChallenge(challenge);
        User user = userRepository.findUserByUserChallenge(userChallenge);
        Boolean isScraped = scrapService.findScrapByUserAndChallenge(user, challenge);

        return ChallengeResDto.ChallengeDto.builder()
                .title(challenge.getChallengeCourseTitle())
                .challengeId(challenge.getId())
                .content(challenge.getChallengeCourseContent())
                .area(challenge.getChallengeCourseArea())
                .image(challenge.getChallengeImage())
                .createdDate(challenge.getCreatedAt())
                .difficulty(challenge.getChallengeCourseDifficulty())
                .isScraped(isScraped)
                .user(UserResDto.UserDto.builder().userId(user.getId()).profileImg(user.getProfileImg()).nickName(user.getNickName()).build())
                .scrapCount(challenge.getScrapCount())
                .build();
    }

    public static List<ChallengeResDto.ChallengeDto> toChallengeDtoList(Page<Challenge> challengeList){
        return challengeList.stream()
                .map(challenge -> toChallengeDto(challenge))
                .collect(Collectors.toList());
    }

    public static ChallengeResDto.ChallengeListDto toChallengeListDto(Page<Challenge> challengeList){
        return ChallengeResDto.ChallengeListDto.builder()
                .challengeList(toChallengeDtoList(challengeList))
                .build();
    }

    public static ChallengeResDto.MyChallengeDto toMyChallengeDto(Challenge challenge){
        UserChallenge userChallenge = userChallengeRepository.findUserChallengeByChallenge(challenge);
        User user = userRepository.findUserByUserChallenge(userChallenge);

        return ChallengeResDto.MyChallengeDto.builder()
                .challengeId(challenge.getId())
                .area(challenge.getChallengeCourseArea())
                .image(challenge.getChallengeImage())
                .createdDate(challenge.getCreatedAt())
                .user(UserResDto.UserDto.builder().userId(user.getId()).profileImg(user.getProfileImg()).nickName(user.getNickName()).build())
                .build();
    }

    public static List<ChallengeResDto.MyChallengeDto> toChallengeDtoMyList(List<Challenge> challengeList){
        return challengeList.stream()
                .map(challenge -> toMyChallengeDto(challenge))
                .collect(Collectors.toList());
    }

    public static ChallengeResDto.MyChallengeListDto toChallengeMyListDto(List<Challenge> challengeList){
        return ChallengeResDto.MyChallengeListDto.builder()
                .ChallengeList(toChallengeDtoMyList(challengeList))
                .build();
    }


}
