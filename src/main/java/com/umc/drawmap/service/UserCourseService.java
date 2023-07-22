package com.umc.drawmap.service;

import com.umc.drawmap.domain.User;
import com.umc.drawmap.domain.UserCourse;
import com.umc.drawmap.dto.userCourse.UserCourseReqDto;
import com.umc.drawmap.exception.NotFoundException;
import com.umc.drawmap.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;
    @Transactional
    public UserCourse create(List<MultipartFile> files, UserCourseReqDto.CreateUserCourseDto request) throws IOException {
        UserCourse userCourse = UserCourse.builder()
                .userCourseTitle(request.getUserCourseTitle())
                .userCourseArea(request.getUserCourseArea())
                .userCourseContent(request.getUserCourseContent())
                .userCourseDifficulty(request.getUserCourseDifficulty())
                .userImage(FileService.fileUpload(files))
                .build();

        return userCourseRepository.save(userCourse);
    }
    @Transactional
    public UserCourse update(Long uCourseId, List<MultipartFile> files, UserCourseReqDto.UpdateUserCourseDto request) throws IOException{
        UserCourse userCourse = userCourseRepository.findById(uCourseId).get();
        userCourse.update(request.getUserCourseTitle(), request.getUserCourseArea(),
                request.getUserCourseDifficulty(), request.getUserCourseContent(),
                FileService.fileUpload(files));
        return userCourse;
    }

    public void delete(Long ucourseId){
        userCourseRepository.deleteById(ucourseId);
    }

//============list===============//
    public UserCourse findById(Long uCourseId){
        return userCourseRepository.findById(uCourseId).get();
    }

    public List<UserCourse> findAllByUser(Long userId){

//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
//        List<UserCourse> userCourseList = userCourseRepository.findAllByUser(user);
        List<UserCourse> list = new ArrayList<>();
//        for (UserCourse userCourse: userCourseList){
//            UserCourse userCourse = userCourseRepository.findUserCourseByUser(user);
//            list.add(userCourse);
//        }

//        Pageable pageable = PageRequest.of(page, size);
        return list;
    }
    public List<UserCourse> findAll(){
        List<UserCourse> userCourseList = userCourseRepository.findAll();
        return userCourseList;
    }

    public List<UserCourse> getPage(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<UserCourse> fetchPages = userCourseRepository.findAllOrderByCreatedAtDesc(pageRequest);
        return fetchPages.getContent();
    }

    public List<UserCourse> getPageByScrap(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<UserCourse> fetchPages = userCourseRepository.findAllOrderByScrapCountDesc(pageRequest);
        return fetchPages.getContent();
    }
}
