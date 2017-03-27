package com.waylau.spring.boot.fileserver.storage;

import com.mongodb.gridfs.GridFSDBFile;
import com.waylau.spring.boot.fileserver.domain.FileInfo;

import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;


public interface FileStorage {

    FileInfo save(InputStream content, String fileName);


    Optional<GridFSDBFile> read(String fileId);


    Stream<GridFSDBFile> getAll();

    void deleteTestFiles(String fileName);
}
