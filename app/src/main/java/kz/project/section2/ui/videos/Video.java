package kz.project.section2.ui.videos;
import java.util.List;

import kz.project.section2.ui.skills.SkillItem;

public class Video {
    String videoPath;
    String videoDur;

    public Video(String videoPath, String videoDur) {
        this.videoPath = videoPath;
        this.videoDur = videoDur;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoDur() {
        return videoDur;
    }

    public void setVideoDur(String videoDur) {
        this.videoDur = videoDur;
    }
}
