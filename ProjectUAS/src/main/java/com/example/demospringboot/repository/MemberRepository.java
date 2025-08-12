package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.stereotype.Repository;


public interface MemberRepository extends JpaRepository<Member, Integer>  {
    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    Member findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value="UPDATE user SET grade = :grade WHERE id = :user_id", nativeQuery = true)
    int updateMemberGrade(int user_id, String grade);
}
