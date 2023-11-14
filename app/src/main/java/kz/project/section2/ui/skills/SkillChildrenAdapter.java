package kz.project.section2.ui.skills;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.section2.R;


public class SkillChildrenAdapter extends RecyclerView.Adapter<SkillChildrenAdapter.ViewHolder> {
    private Context context;
    private List<SkillItem> eventsItemList;

    public SkillChildrenAdapter(Context context, List<SkillItem> eventsItemList) {
        this.context = context;
        this.eventsItemList = eventsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_children_skills, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.skillName.setText(eventsItemList.get(position).getTitle());

        holder.skillName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialogInfo(holder, position);
                holder.dialog.show();
                dPos = 0;

                if(position+dPos == eventsItemList.size()-1){
                    holder.btnNext.setTextColor(context.getResources().getColor(R.color.grey));
                    holder.btnNext.setEnabled(false);
                }

                holder.btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dPos++;
                        if(position+dPos == eventsItemList.size()-1){
                            holder.btnNext.setTextColor(context.getResources().getColor(R.color.grey));
                            holder.btnNext.setEnabled(false);
                        }

                        setDialogInfo(holder, position+dPos);
                    }
                });

            }
        });

    }
    int dPos = 0;

    public void setDialogInfo(ViewHolder holder, int position){
        holder.skillDialogName.setText(eventsItemList.get(position).getTitle());
        holder.skillDesc.setText(eventsItemList.get(position).getDesc());

        String imagefile = eventsItemList.get(position).getImagePath();
        String resName = imagefile.split("\\.")[2];

        holder.skillImage.setImageResource(
                context.getResources().getIdentifier(
                        resName,
                        "drawable", context.getPackageName()));


        if(position+dPos == eventsItemList.size()-1){
            holder.btnNext.setTextColor(context.getResources().getColor(R.color.grey));
            holder.btnNext.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return eventsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView skillName;
        Dialog dialog;

        TextView skillDialogName;
        ImageView skillImage;
        TextView skillDesc;
        Button btnNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillName = itemView.findViewById(R.id.skillName);
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_skill);

            skillDialogName = dialog.findViewById(R.id.skillName);
            skillImage = dialog.findViewById(R.id.skillImage);
            skillDesc = dialog.findViewById(R.id.skillDesc);
            btnNext = dialog.findViewById(R.id.btnNext);

        }
    }
}
