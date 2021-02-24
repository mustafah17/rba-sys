package io.softera.rba.repository;

import io.softera.rba.domain.Table;
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
class TableRepositoryTest {

    @Resource
    private TableRepository tableRepository;

    @Test
    void saveTest() {
        //Given a table
        final Table t = new Table();
        t.setTableNo(2);
        t.setRestaurantId(330);
        t.setTableSize(4);

        //When we save the table
        int count = tableRepository.save(t);

        //Then
        assertEquals(1, count);
    }

    @Test
    void findBySizeTest() {
        //Given we insert a table
        final Table t = new Table();
        t.setTableNo(2);
        t.setRestaurantId(330);
        t.setTableSize(4);
        tableRepository.save(t);

        //When we search for size 4
        Table test = tableRepository.findBySize(4);

        //Then
        assertEquals(4, test.getTableSize());
    }
}