package com.waylau.spring.boot.fileserver.storage;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.waylau.spring.boot.fileserver.domain.FileInfo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class FileStorageGridFs implements FileStorage {

    private final GridFsOperations gridOperations;

    public FileStorageGridFs(GridFsOperations gridOperations) {
        this.gridOperations = gridOperations;
    }


    @Override
    public FileInfo save(InputStream content, String fileName) {
        return toFileInfo(gridOperations.store(content,fileName));
    }


    @Override
    public Optional<GridFSDBFile> read(String fileId) {
        GridFSDBFile file;

        try {
            Query query = Query.query(Criteria.where("_id").is(new ObjectId(fileId)));
            file = gridOperations.findOne(query);
        }
        catch (IllegalArgumentException ex){
            return Optional.empty();
        }

        return file ==null? Optional.empty():Optional.of(file);
    }


    @Override
    public Stream<GridFSDBFile> getAll() {

        List<GridFSDBFile> allFiles = gridOperations.find(new Query());

        return allFiles.stream();
    }


    @Override
    public void deleteTestFiles(String testFileName){
        Query query = new Query(Criteria.where("filename").is(testFileName));
        gridOperations.delete(query);
    }

    private static FileInfo toFileInfo(GridFSFile gridFile) {
        FileInfo fileInfo = new FileInfo();

        fileInfo.setFilename(gridFile.getFilename());
        fileInfo.setAliases(gridFile.getAliases());
        fileInfo.setChunkSize(gridFile.getChunkSize());
        fileInfo.setContentType(gridFile.getContentType());
        fileInfo.setLength(gridFile.getLength());
        fileInfo.setMd5(gridFile.getMD5());
        fileInfo.setId(((ObjectId) gridFile.getId()).toHexString());
        fileInfo.setUploadDate(gridFile.getUploadDate());
        return fileInfo;
    }


}
