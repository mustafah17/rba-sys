package io.softera.rba.repository;

import io.softera.rba.domain.Admin;
import io.softera.rba.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AdminRepositoryTest {

    public static final String EMAIL = "musatafa@gmail";
    @Resource
    private AdminRepository adminRepository;

    @Test
    void save() {
        //Given an Admin
        final Admin a = new Admin();
        a.setEmail("testEmail");
        a.setFullName("testName");
        a.setPhoneNo("testPhone");

        //When we save
        int count = adminRepository.save(a);

        //Then
        assertEquals(1, count);
    }

    @Test
    void findByEmail() {
        //Given a saved admin
        final Admin a = new Admin();
        a.setEmail(EMAIL);
        a.setFullName("testName");
        a.setPhoneNo("testPhone");
        adminRepository.save(a);

        //When we find by email
        Admin test = adminRepository.findByEmail(EMAIL);

        //Then
        assertEquals(EMAIL, test.getEmail());
    }
}