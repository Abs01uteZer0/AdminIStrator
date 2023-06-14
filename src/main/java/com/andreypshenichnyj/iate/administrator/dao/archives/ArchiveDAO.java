package com.andreypshenichnyj.iate.administrator.dao.archives;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;

import java.util.List;

public interface ArchiveDAO {

    List<Archives> getAllArchives();

    void addArchive(Archives archive);

    Archives getArchive(int id);

    List<Archives> getAllActiveArchiveScripts();

    List<Archives> getAllStashedArchiveScripts();
}
