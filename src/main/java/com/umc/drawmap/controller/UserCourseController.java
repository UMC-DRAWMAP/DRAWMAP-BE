package com.umc.drawmap.controller;

import com.umc.drawmap.dto.userCourse.UserCourseConverter;
import com.umc.drawmap.dto.userCourse.UserCourseReqDto;
import com.umc.drawmap.dto.userCourse.UserCourseResDto;
import com.umc.drawmap.exception.BaseResponse;
import com.umc.drawmap.service.UserCourseService;
import com.umc.drawmap.domain.UserCourse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserCourseController {
    private final UserCourseService userCourseService;

    // 등록
    @PostMapping("/usercourse")
    public BaseResponse<String> createUserCourse(@RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                @ModelAttribute(value= "request") UserCourseReqDto.CreateUserCourseDto request
    ) throws IOException {
        UserCourse userCourse = userCourseService.create(files, request);
        return new BaseResponse<>("유저코스 등록 완료");
    }

    // 수정
    @PatchMapping("/usercourse/{ucourseId}")
    public BaseResponse<String> updateUserCourse(@PathVariable(name = "ucourseId")Long ucourseId,
                                                @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                @ModelAttribute UserCourseReqDto.UpdateUserCourseDto request
    ) throws IOException{
        UserCourse userCourse = userCourseService.update(ucourseId, files, request);
        return new BaseResponse<>("유저코스 수정 완료");
    }

    // 삭제
    @DeleteMapping("usercourse/{ucourseId}")
    public BaseResponse<String> deleteUserCourse(@PathVariable(name = "ucourseId")Long ucourseId){
        userCourseService.delete(ucourseId);
        return new BaseResponse<>("유저코스 삭제 완료");
    }

    // 전체 리스트 조회
    @GetMapping("usercourse")
    public BaseResponse<List<UserCourseResDto.UserCourseDto>> getUserCourseList(){
        List<UserCourse> userCourseList = userCourseService.findAll();
        return new BaseResponse<>(UserCourseConverter.toUserCourseDtoList(userCourseList));
    }

    // 본인 리스트 조회
    @GetMapping("usercourse/{userId}/courses")
    public BaseResponse<List<UserCourseResDto.MyUserCourseDto>> getUserCourseMyList(@PathVariable(name = "userId")Long userId){
        List<UserCourse> userCourseMyList = userCourseService.findAllByUser(userId);
        return new BaseResponse<>(UserCourseConverter.toUserCourseDtoMyList(userCourseMyList));
    }

    // 페이지 조회
    @GetMapping("usercourse/{ucourseId}")
    public BaseResponse<UserCourseResDto.UserCourseDto> getUserCourse(@PathVariable(name = "ucourseId")Long ucourseId){
        UserCourse userCourse = userCourseService.findById(ucourseId);
        return new BaseResponse<>(UserCourseConverter.toUserCourseDto(userCourse));
    }

    // 유저개발코스 정렬(최신순)
    @GetMapping("/usercourse/list")
    public BaseResponse<List<UserCourseResDto.UserCourseSortDto>> getList(@RequestParam(name = "page")int page, @RequestParam(name = "size")int size){
        List<UserCourse> userCourseList = userCourseService.getPage(page, size);
        return new BaseResponse<>(UserCourseConverter.toUserCourseSortDto(userCourseList));
    }

    // 유저개발코스 정렬(인기순)
    @GetMapping("/usercourse/scraplist")
    public BaseResponse<List<UserCourseResDto.UserCourseSortDto>> getListByScrap(@RequestParam(name = "page")int page, @RequestParam(name = "size")int size){
        List<UserCourse> userCourseList = userCourseService.getPageByScrap(page, size);
        return new BaseResponse<>(UserCourseConverter.toUserCourseSortDto(userCourseList));
    }

}
