package fr.fortytwo.cinema.models;

import java.util.Objects;

public class FileMapping {
    private String originalFileName;
    private String generatedFileName;
    private String mimeType;
    private Integer size;
    private String path;

    public FileMapping() {

    }

    public FileMapping(String originalFileName, String generatedFileName, String mimeType, Integer size, String path) {
        this.originalFileName = originalFileName;
        this.generatedFileName = generatedFileName;
        this.mimeType = mimeType;
        this.size = size;
        this.path = path;
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

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    

    @Override
    public String toString() {
        return "{" +
                " originalFileName='" + getOriginalFileName() + "'" +
                ", generatedFileName='" + getGeneratedFileName() + "'" +
                ", mimeType='" + getMimeType() + "'" +
                ", size='" + getSize() + "'" +
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
                && Objects.equals(mimeType, fileMapping.mimeType) && Objects.equals(size, fileMapping.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalFileName, generatedFileName, mimeType, size);
    }

}
