package io.softera.rba.repository;

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
class UserRepositoryTest {

    public static final String EMAIL = "musatafa@gmail";
    @Resource
    private UserRepository userRepository;

    @Test
    void insertionTest() {
        //Given
        final User aUser = new User();
        aUser.setEmail("testEmail");
        aUser.setFullName("testName");
        aUser.setPhoneNo("testPhone");

        //When
        int count = userRepository.save(aUser);

        //Then
        assertEquals(1, count);
    }


    @Test
    void findByEmailTest() {
        //Given
        final User aUser = new User();
        aUser.setEmail(EMAIL);
        aUser.setFullName("mustafa");
        aUser.setPhoneNo("07777");
        userRepository.save(aUser);

        //When
        User test = userRepository.findByEmail(EMAIL);

        //Then
        assertEquals(EMAIL, test.getEmail());
    }

}