package com.andreypshenichnyj.iate.administrator.dao.journals;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Journals;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalsDAOImpl implements JournalsDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Journals> getAllJournals() {
        Query query = entityManager.createQuery("from Journals", Journals.class);
        List<Journals> allJournals = query.getResultList();

        return allJournals;
    }

    @Override
    public void addJournal(Journals journal) {
        if (journal.getJournal_id() == 0){
            entityManager.persist(journal);
        } else {
            entityManager.merge(journal);
        }
    }

    @Override
    public Journals getJournal(int id) {
        Journals journal = entityManager.find(Journals.class, id);
        return journal;
    }
}
