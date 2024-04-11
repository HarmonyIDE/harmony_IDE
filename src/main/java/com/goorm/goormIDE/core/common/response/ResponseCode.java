//package com.goorm.goormIDE.core.common.response;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//
//@Getter
//@AllArgsConstructor
//public enum ResponseCode {
//    SERVER_ERROR(500,"SERVER-ERR","잠시 후 다시 시도해주세요."),
//    BAD_REQUEST(400,"CLIENT-ERR","잘못된 요청입니다."),
//
//    //Success Status
//    SUCCESS_INSERT(201,"INSERT-SUCCESS", "등록 성공 하였습니다."),
//    SUCCESS_UPDATE(201,"UPDATE-SUCCESS", "수정 성공 하였습니다."),
//    SUCCESS_DELETE(204,"DELETE-SUCCESS", "삭제 성공 하였습니다."),
//    SUCCESS_SELECT(200,"SELECT-SUCCESS", "조회 성공 하였습니다."),
//
//    //404
//    RESTAURANT_NOT_FOUND(404,"RESTAURANT-ERR","해당 매장을 찾을 수 없습니다."),
//    CUSTOMER_NOT_FOUND(404,"CUSTOMER-ERR","해당 손님을 찾을 수 없습니다.");
//
//
//    private final int status;
//    private final String code;
//    private final String message;
//}
