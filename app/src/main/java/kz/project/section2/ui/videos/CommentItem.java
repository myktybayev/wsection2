package kz.project.section2.ui.videos;

public class CommentItem {
    String fromText;
    String commentText;

    public CommentItem(String fromText, String commentText) {
        this.fromText = fromText;
        this.commentText = commentText;
    }

    public String getFromText() {
        return fromText;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
