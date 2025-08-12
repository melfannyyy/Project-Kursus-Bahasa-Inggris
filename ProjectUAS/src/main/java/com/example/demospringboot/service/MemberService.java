package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Member;
import com.example.demospringboot.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements BaseService<Member, Integer>{
    @Autowired
    private MemberRepository memberRepository;

    // Implementasi metode dari BaseService
    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member save(Member admin) {
        return memberRepository.save(admin);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    public Member getMemberById(int id) {
        return memberRepository.findById(id).orElse(null);
    }
    public Member addMember(Member member) {
        String pass = member.getPassword();
        String hashedpass = member.getEncryptedPass(pass);
        member.setPassword(hashedpass);
        return memberRepository.save(member);
    }
    public Member updateMember(int id, Member member) {
        member.setId(id);
        return memberRepository.save(member);
    }
    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }
    
    public Member getMemberByMembername(String membername) {
        return memberRepository.findByUsername(membername);
    }

    // public void updateMemberGrade(int member_id, String grade) {
    //     memberRepository.updateMemberGrade(member_id, grade);
    // }
}
