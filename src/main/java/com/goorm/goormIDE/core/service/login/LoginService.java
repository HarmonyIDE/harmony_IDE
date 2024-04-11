package com.goorm.goormIDE.core.service.login;

import com.goorm.goormIDE.domain.primary.login.repository.LoginRepository;
import com.goorm.goormIDE.core.dto.request.member.MemberDto;
import com.goorm.goormIDE.domain.primary.login.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<MemberDto> chkAdmin(String id,String pw) {
        Member checkAdmin = loginRepository.findByUserId(id);

        if (checkAdmin != null && passwordEncoder.matches(pw, checkAdmin.getPassword())) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUserId(checkAdmin.getUserId());
            memberDto.setPhoneNum(checkAdmin.getPhoneNum());
            memberDto.setAdminYn(checkAdmin.getAdminYn());
            memberDto.setMemberSeq(Long.valueOf(checkAdmin.getMemberSeq()));
            return ResponseEntity.ok(memberDto);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @Transactional
    public boolean registerUser(MemberDto memberDto){
        try {
            Member existingUser = loginRepository.findByUserId(memberDto.getUserId());
            if (existingUser != null) {
                return false;
            }
            String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
            Member insMember = Member.builder()
                    .userId(memberDto.getUserId())
                    .password(encodedPassword) // 비밀번호 암호화
                    .phoneNum(memberDto.getPhoneNum())
                    .adminYn("N")
                    .regTime(LocalDateTime.now()) // 현재 시간 등록
                    .build();
            loginRepository.save(insMember);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    @Transactional
//    public void registerReservation(ReservationDto reservationDto){
//        Member member = memberRepository.findById(Long.valueOf(reservationDto.getMemberSeq()))
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        Reservation reservation = Reservation.builder()
//                .reservationTime(reservationDto.getReservationTime())
//                .peopleNum(reservationDto.getPeopleNum())
//                .phoneNum(reservationDto.getPhoneNum())
//                .extra(reservationDto.getExtra())
//                .status(reservationDto.getStatus())
//                .member(member) // 조회한 Member 엔티티 설정
//                .build();
//        reservationRepository.save(reservation);
//
//    }
}
