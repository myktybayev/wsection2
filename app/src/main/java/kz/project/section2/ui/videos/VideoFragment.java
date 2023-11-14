package kz.project.section2.ui.videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;
import kz.project.section2.ui.photos.PhotoItem;
import kz.project.section2.ui.photos.PhotosAdapter;

public class VideoFragment extends Fragment {
    View view;
    RecyclerView recyclerViewMoreVideo;
    List<Video> videoList;
    MoreVideoAdapter moreVideoAdapter;


    List<CommentItem> commentItemList;
    CommentListAdapter commentListAdapter;
    RecyclerView recyclerViewComment;
    TextView commentCount;
    EditText et_comment;
           Button btn_comment;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_video, container, false);
        et_comment = view.findViewById(R.id.et_comment);
        btn_comment = view.findViewById(R.id.btn_comment);

        recyclerViewMoreVideo = view.findViewById(R.id.recyclerViewMoreVideo);
        videoList = new ArrayList<>();
        videoList.add(new Video("", "1:00"));
        videoList.add(new Video("", "5:00"));
        videoList.add(new Video("", "4:00"));
        videoList.add(new Video("", "4:00"));
        videoList.add(new Video("", "4:00"));

        moreVideoAdapter = new MoreVideoAdapter(getActivity(), videoList);
        recyclerViewMoreVideo.setAdapter(moreVideoAdapter);


        commentCount = view.findViewById(R.id.commentCount);
        recyclerViewComment = view.findViewById(R.id.recyclerViewComment);
        commentItemList = new ArrayList<>();
        commentItemList.add(new CommentItem("192.168", "Hello"));
        commentItemList.add(new CommentItem("193.167", "Hi"));
        commentItemList.add(new CommentItem("193.167", "Hi"));
        commentItemList.add(new CommentItem("193.167", "Hi"));

        commentCount.setText(commentItemList.size()+" comments");
        commentListAdapter = new CommentListAdapter(getActivity(), commentItemList);
        recyclerViewComment.setAdapter(commentListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewComment.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewComment.addItemDecoration(dividerItemDecoration);


                btn_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commentItemList.add(new CommentItem("Localhost", et_comment.getText().toString()));
                        commentListAdapter.notifyDataSetChanged();
                        commentCount.setText(commentItemList.size()+" comments");
                        et_comment.setText("");
                    }
                });
        return view;
    }
}
