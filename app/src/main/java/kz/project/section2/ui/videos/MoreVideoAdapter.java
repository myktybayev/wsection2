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
import kz.project.section2.ui.skills.SkillChildrenAdapter;
import kz.project.section2.ui.skills.SkillType;


public class MoreVideoAdapter extends RecyclerView.Adapter<MoreVideoAdapter.ViewHolder> {
    private Context context;
    private List<Video> videoList;

    public MoreVideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.video_dur.setText(videoList.get(position).getVideoDur());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView video_dur;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            video_dur = itemView.findViewById(R.id.video_dur);
        }
    }
}
