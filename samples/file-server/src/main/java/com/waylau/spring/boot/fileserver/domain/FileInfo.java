package com.waylau.spring.boot.fileserver.domain;

import java.util.Date;
import java.util.List;


public class FileInfo {
    private String filename;
    private String contentType;
    private long length;
    private long chunkSize;
    private Date uploadDate;
    private List<String> aliases;
    private String md5;
    private String id;

    public FileInfo() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Date getUploadDate() {
        return new Date(uploadDate.getTime());
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = new Date(uploadDate.getTime());
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        FileInfo fileInfo = (FileInfo) object;
        return java.util.Objects.equals(length, fileInfo.length)
                && java.util.Objects.equals(chunkSize, fileInfo.chunkSize)
                && java.util.Objects.equals(filename, fileInfo.filename)
                && java.util.Objects.equals(contentType, fileInfo.contentType)
                && java.util.Objects.equals(uploadDate, fileInfo.uploadDate)
                && java.util.Objects.equals(aliases, fileInfo.aliases)
                && java.util.Objects.equals(md5, fileInfo.md5)
                && java.util.Objects.equals(id, fileInfo.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(filename, contentType, length, chunkSize, uploadDate, aliases, md5, id);
    }

    @Override
    public String toString() {
        return "FileInfo{"
                + "filename='" + filename + '\''
                + ", contentType='" + contentType + '\''
                + ", length=" + length
                + ", chunkSize=" + chunkSize
                + ", uploadDate=" + uploadDate
                + ", aliases=" + aliases
                + ", md5='" + md5 + '\''
                + ", id='" + id + '\''
                + '}';
    }
}
