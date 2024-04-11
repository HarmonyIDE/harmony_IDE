//package com.goorm.goormIDE.domain.common;
//
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import jakarta.persistence.Column;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.MappedSuperclass;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//
//@MappedSuperclass // 테이블로 생성되지 않도록 해주는 어노테이션
//@EntityListeners(value = {AuditingEntityListener.class}) // AuditingEntityListener : JPA 내부에서 엔티티 객체가 생성/변경 되는것을 감지하는 역할
//@Getter
//@Setter
//@JsonSerialize
//@JsonDeserialize
//public class BaseEntity {
//
//    @CreatedDate
//    @Column(name = "CREATED_AT",updatable = false)
//    private LocalDateTime createdAt;
//
//    @CreatedBy
//    @Column(name = "CREATED_BY",updatable = false)
//    private Long createdBy;
//
//    @LastModifiedDate
//    @Column(name = "LAST_MODIFIED_AT")
//    private LocalDateTime lastModifiedAt;
//
//    @LastModifiedBy
//    @Column(name = "LAST_MODIFIED_BY")
//    private Long lastModifiedBy;
//
//    @Column(name = "DELETED", nullable = false, columnDefinition = "boolean default false")
//    private boolean deleted;
//
//}
