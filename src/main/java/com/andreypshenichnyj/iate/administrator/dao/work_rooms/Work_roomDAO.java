package com.andreypshenichnyj.iate.administrator.dao.work_rooms;

import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;

import java.util.List;

public interface Work_roomDAO {
    List<Work_rooms> getAllWorkRooms();

    Work_rooms getWorkRoomById(int id);
}
