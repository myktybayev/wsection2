package kz.project.section2.ui.videos;
import java.util.List;

public class Video {
    String videoName;
    String videoPath;
    String videoDur;
    List<CommentItem> commentItemList;

    public Video(String videoName,String videoPath, String videoDur, List<CommentItem> commentItemList) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoDur = videoDur;
        this.commentItemList = commentItemList;
    }

    public List<CommentItem> getCommentItemList() {
        return commentItemList;
    }

    public void setCommentItemList(List<CommentItem> commentItemList) {
        this.commentItemList = commentItemList;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
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
