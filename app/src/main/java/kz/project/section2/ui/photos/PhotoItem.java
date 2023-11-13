package kz.project.section2.ui.photos;

public class PhotoItem {
    String imagePath;
    String popularity;
    String visit;

    public PhotoItem(String imagePath, String popularity, String visit) {
        this.imagePath = imagePath;
        this.popularity = popularity;
        this.visit = visit;
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
