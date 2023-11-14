package kz.project.section2.ui.videos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.section2.R;


public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    private Context context;
    private List<CommentItem> commentItemList;

    public CommentListAdapter(Context context, List<CommentItem> commentItemList) {
        this.context = context;
        this.commentItemList = commentItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fromText.setText(commentItemList.get(position).getFromText());
        holder.commentText.setText(commentItemList.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        return commentItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fromText;
        TextView commentText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fromText = itemView.findViewById(R.id.fromText);
            commentText = itemView.findViewById(R.id.commentText);
        }
    }
}
