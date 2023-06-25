package com.andreypshenichnyj.iate.administrator.dao.journals;

import com.andreypshenichnyj.iate.administrator.entity.Journals;

import java.util.List;

public interface JournalsDAO {

    List<Journals> getAllJournals();

    void addJournal(Journals journal);

    Journals getJournal(int id);
}
