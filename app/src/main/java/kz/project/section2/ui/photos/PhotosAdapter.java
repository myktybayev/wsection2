package kz.project.section2.ui.photos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.section2.R;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private Context context;
    private List<PhotoItem> photoItemList;

    public PhotosAdapter(Context context, List<PhotoItem> photoItemList) {
        this.context = context;
        this.photoItemList = photoItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imagefile = photoItemList.get(position).getImagePath();
        String resName = imagefile.split("\\.")[2];

        holder.im_photo.setImageResource(
                context.getResources().getIdentifier(
                        resName,
                        "drawable", context.getPackageName()));

        holder.tv_popularity.setText(photoItemList.get(position).getPopularity());
        holder.tv_visit.setText(photoItemList.get(position).getVisit());

    }

    @Override
    public int getItemCount() {
        return photoItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView im_photo;
        TextView tv_popularity;
        TextView tv_visit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            im_photo = itemView.findViewById(R.id.im_photo);
            tv_popularity = itemView.findViewById(R.id.tv_popularity);
            tv_visit = itemView.findViewById(R.id.tv_visit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ImageWebViewActivity.class);
                    intent.putExtra("photoItem", photoItemList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    PopupMenu popup = new PopupMenu(itemView.getContext(), itemView);
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {

                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Image link: "+photoItemList.get(getAdapterPosition()).getImagePath());
                            sendIntent.setType("text/plain");

                            Intent shareIntent = Intent.createChooser(sendIntent, null);
                            context.startActivity(shareIntent);

                            return true;
                        }
                    });
                    popup.show(); //showing popup menu
                    return false;
                }
            });
        }
    }
}
