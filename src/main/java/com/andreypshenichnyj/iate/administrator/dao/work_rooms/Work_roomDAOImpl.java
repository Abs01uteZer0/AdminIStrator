package com.andreypshenichnyj.iate.administrator.dao.work_rooms;

import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Work_roomDAOImpl implements Work_roomDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Work_rooms> getAllWorkRooms() {
        Query query = entityManager.createQuery("from Work_rooms", Work_rooms.class);
        List<Work_rooms> allWorkRooms = query.getResultList();

        return allWorkRooms;
    }

    @Override
    public Work_rooms getWorkRoomById(int id) {
        Work_rooms work_room = entityManager.find(Work_rooms.class, id);
        return work_room;
    }
}
