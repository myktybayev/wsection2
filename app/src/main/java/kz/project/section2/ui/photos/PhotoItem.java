package kz.project.section2.ui.photos;

import java.io.Serializable;

public class PhotoItem implements Serializable {
    int photoId;
    String imagePath;
    String popularity;
    String visit;

    public PhotoItem(int photoId, String imagePath, String popularity, String visit) {
        this.photoId = photoId;
        this.imagePath = imagePath;
        this.popularity = popularity;
        this.visit = visit;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}
