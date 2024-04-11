package com.goorm.goormIDE.domain.primary.login.repository;

import com.goorm.goormIDE.domain.primary.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Member,Long> {
    public Member findByUserId(String id);
}
