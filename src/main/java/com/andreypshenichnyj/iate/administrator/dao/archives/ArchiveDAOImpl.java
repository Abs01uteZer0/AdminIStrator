package com.andreypshenichnyj.iate.administrator.dao.archives;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArchiveDAOImpl implements ArchiveDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Archives> getAllArchives() {
        Query query = entityManager.createQuery("from Archives", Archives.class);
        List<Archives> allArchives = query.getResultList();

        return allArchives;
    }

    @Override
    public void addArchive(Archives archive) {
        if (archive.getArchive_id() == 0){
            entityManager.persist(archive);
        } else {
            entityManager.merge(archive);
        }
    }

    @Override
    public Archives getArchive(int id) {
        Archives archive = entityManager.find(Archives.class, id);
        return archive;
    }

    @Override
    public List<Archives> getAllActiveArchiveScripts() {
        return getAllArchives().stream().filter((arch -> arch.isStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Archives> getAllStashedArchiveScripts() {
        return getAllArchives().stream().filter((arch -> !arch.isStatus())).collect(Collectors.toList());
    }
}
