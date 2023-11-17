package kz.project.section2.ui.skills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.section2.R;


public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {
    private Context context;
    private List<SkillType> eventsItemList;

    public SkillsAdapter(Context context, List<SkillType> eventsItemList) {
        this.context = context;
        this.eventsItemList = eventsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_skills,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.skillType.setText(eventsItemList.get(position).getSkillType());

        SkillChildrenAdapter skillChildrenAdapter = new SkillChildrenAdapter(context, eventsItemList.get(position).getSkillItems());
        holder.skillItems.setAdapter(skillChildrenAdapter);
    }

    @Override
    public int getItemCount() {
        return eventsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView skillType;
        RecyclerView skillItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillType = itemView.findViewById(R.id.skillType);
            skillItems = itemView.findViewById(R.id.skillItems);
        }
    }
}
