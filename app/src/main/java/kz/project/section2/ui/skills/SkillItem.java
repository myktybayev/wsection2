package kz.project.section2.ui.skills;

public class SkillItem {
    String title;
    String desc;
    String imagePath;

    public SkillItem(String title, String desc, String imagePath) {
        this.title = title;
        this.desc = desc;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
