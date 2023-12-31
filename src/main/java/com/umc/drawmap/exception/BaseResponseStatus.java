package com.umc.drawmap.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    FAIL(false, 1001, "요청에 실패하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),

    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),


    //이메일 닉네임 오류
    TOO_SHORT_PASSWORD(false, 2018, "비밀번호의 길이를 8자 이상을 설정해주세요."),

    FAILED_TO_SIGN_UP(false, 2019, "회원가입에 실패하였습니다."),
    USERS_EXISTS_NICKNAME(false,2021,"중복된 닉네임입니다."),

    USERS_EMPTY_NICKNAME(false,2022,"닉네임을 입력해주세요."),
    FAILED_TO_LOG_IN(false, 2023, "로그인에 실패하였습니다."),
    NOT_EXIST_USER_ID(false, 2024, "존재하지 않는 유저 id입니다."),
    NOT_EXIST_NICKNAME(false, 2025, "존재하지 않는 닉네임입니다."),
    NOT_EXIST_EMAIL(false, 2026, "존재하지 않는 이메일입니다."),
    NOT_CORRECT_PASSWORD(false, 2027, "비밀번호가 일치하지 않습니다."),
    ALREADY_DELETED_USER(false, 2028, "이미 탈퇴된 유저입니다."),
    FAILED_TO_UPDATE_PASSWORD(false, 2029, "새 비밀번호 설정에 실패했습니다"),
    FAILED_TO_UPDATE_NICKNAME(false, 2030, "새 닉네임 설정에 실패했습니다"),
    ROLE_EXCEPTION(false, 2031, "관리자 권한으로 먼저 설정을 해주세요."),

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),

    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),





    /**
     * 5000 : board 관련 오류
     */
    ALREADY_DELETED_ERROR(false, 5001, "이미 삭제된 게시물입니다."),

    NOT_FOUND_ID_ERROR(false, 5002, "존재하지 않는 게시글입니다."),

    UPDATE_FAILED_ERROR(false, 5003, "게시글 업데이트에 실패했습니다."),

    EMPTY_LIST_ERROR(false, 5004, "결과 리스트를 찾을 수 없습니다."),

    CREATE_FAILED_ERROR(false, 5005, "게시글 작성에 실패했습니다."),

    NOT_FOUND_NICKNAME_ERROR(false, 5006, "존재하지 않는 닉네임입니다."),
    NEED_INFO_TO_UPDATE(false, 5007, "수정할 내용을 입력해주세요"),


    ;




    // 6000 : 필요시 만들어서 쓰세요






    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}