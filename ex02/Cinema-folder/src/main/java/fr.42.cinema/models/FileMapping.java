package fr.fortytwo.cinema.models;

import java.util.Objects;

public class FileMapping {

    private String originalFileName;
    private String generatedFileName;
    private String mimeType;
    private Long size;
    private String path;
    private Long userId;

    public FileMapping() {

    }

    public FileMapping(String originalFileName, String generatedFileName, String mimeType, Long size, String path,
            Long userId) {
        this.originalFileName = originalFileName;
        this.generatedFileName = generatedFileName;
        this.mimeType = mimeType;
        this.size = size;
        this.path = path;
        this.userId = userId;
    }

    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getGeneratedFileName() {
        return this.generatedFileName;
    }

    public void setGeneratedFileName(String generatedFileName) {
        this.generatedFileName = generatedFileName;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                " originalFileName='" + getOriginalFileName() + "'" +
                ", generatedFileName='" + getGeneratedFileName() + "'" +
                ", mimeType='" + getMimeType() + "'" +
                ", size='" + getSize() + "'" +
                ", path='" + getPath() + "'" +
                ", userId='" + getUserId() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FileMapping)) {
            return false;
        }
        FileMapping fileMapping = (FileMapping) o;
        return Objects.equals(originalFileName, fileMapping.originalFileName)
                && Objects.equals(generatedFileName, fileMapping.generatedFileName)
                && Objects.equals(mimeType, fileMapping.mimeType) && Objects.equals(size, fileMapping.size)
                && Objects.equals(path, fileMapping.path) && Objects.equals(userId, fileMapping.userId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(originalFileName, generatedFileName, mimeType, size, path, userId);
    }

}
