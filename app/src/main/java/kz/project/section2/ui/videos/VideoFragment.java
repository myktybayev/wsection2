package kz.project.section2.ui.videos;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

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
import java.util.Timer;
import java.util.TimerTask;

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
    TextView commentCount, tv_play, tv_duration, tv_sound;
    EditText et_comment;
    Button btn_comment;


    VideoView videoView;
    boolean playBoolean;
    boolean mute = true;
    ProgressBar videoProgressBar;
    String durFormat;
    int currentDuration = 0;
    int videoDuration;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);

        et_comment = view.findViewById(R.id.et_comment);
        btn_comment = view.findViewById(R.id.btn_comment);
        tv_play = view.findViewById(R.id.tv_play);
        tv_sound = view.findViewById(R.id.tv_sound);
        tv_duration = view.findViewById(R.id.duration);//0:00 / 1:00
        videoView = view.findViewById(R.id.videoView);
        videoProgressBar = view.findViewById(R.id.videoProgressBar);

//        load from url
        videoView.setVideoPath("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");

//        load from file
//        videoView.setVideoURI(Uri.parse(path+filename));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                tv_play.setEnabled(true);
                videoProgressBar.setVisibility(View.GONE);
                videoDuration = videoView.getDuration();

                double dur = (double) videoDuration / 100000;
                durFormat = String.format("%.2f", dur);
                tv_duration.setText("0:00 / " + durFormat);

                countDownTimer = new CountDownTimer(videoDuration, 1000) {
                    public void onTick(long millisUntilFinished) {
                        currentDuration++;
                        String durText = "";

                        if (currentDuration <= 9) durText = "0:0" + currentDuration;
                        else durText = "0:" + currentDuration;

                        tv_duration.setText(durText + " / " + durFormat);

                    }

                    public void onFinish() {
                    }
                };

            }
        });


        tv_play.setOnClickListener(view -> {
            if (playBoolean) {
                tv_play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                videoView.start();
                countDownTimer.start();

            } else {
                tv_play.setBackgroundResource(R.drawable.baseline_play_arrow_24);
                videoView.pause();
                countDownTimer.cancel();
            }
            playBoolean = !playBoolean;

        });

        tv_sound.setOnClickListener(view1 -> {
            if (mute) {
                tv_sound.setBackgroundResource(R.drawable.baseline_volume_off_24);
                mediaPlayer.setVolume(0f, 0f);
            } else {
                tv_sound.setBackgroundResource(R.drawable.baseline_volume_up_24);
                mediaPlayer.setVolume(100f, 100f);
            }

            mute = !mute;

//            videoView.setOnPreparedListener(mediaPlayer -> {
//                mediaPlayer.setVolume(0f, 0f);
//            });
        });

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

        commentCount.setText(commentItemList.size() + " comments");
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
                commentCount.setText(commentItemList.size() + " comments");
                et_comment.setText("");
            }
        });
        return view;
    }
}
