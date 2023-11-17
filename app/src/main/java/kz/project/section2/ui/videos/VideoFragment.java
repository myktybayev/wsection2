package kz.project.section2.ui.videos;

import android.media.MediaPlayer;
import android.net.Uri;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;

public class VideoFragment extends Fragment implements ItemClick {
    View view;
    RecyclerView recyclerViewMoreVideo;
    List<Video> videoList;
    MoreVideoAdapter moreVideoAdapter;

    List<CommentItem> commentItemList;
    CommentListAdapter commentListAdapter;
    RecyclerView recyclerViewComment;
    TextView videoTitle, commentCount, tv_play, tv_duration, tv_sound;
    EditText et_comment;
    Button btn_comment;

    VideoView videoView;
    boolean playBoolean = true;
    boolean mute = true;
    ProgressBar videoProgressBar;
    String durFormat;
    int currentDuration = 0;
    int videoDuration;
    CountDownTimer countDownTimer;
//    MediaPlayer mediaPlayer;

    int minuteCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);

        initView();

        getDataFromJsonFile();

        loadVideos();


        return view;
    }

    public void initView() {
        et_comment = view.findViewById(R.id.et_comment);
        btn_comment = view.findViewById(R.id.btn_comment);
        tv_play = view.findViewById(R.id.tv_play);
        tv_sound = view.findViewById(R.id.tv_sound);
        tv_duration = view.findViewById(R.id.duration);//0:00 / 1:00
        videoView = view.findViewById(R.id.videoView);
        videoProgressBar = view.findViewById(R.id.videoProgressBar);
        videoTitle = view.findViewById(R.id.videoTitle);

        commentCount = view.findViewById(R.id.commentCount);
        recyclerViewComment = view.findViewById(R.id.recyclerViewComment);
        commentItemList = new ArrayList<>();

        recyclerViewMoreVideo = view.findViewById(R.id.recyclerViewMoreVideo);
        videoList = new ArrayList<>();

    }

    public void loadVideos() {
        moreVideoAdapter = new MoreVideoAdapter(getActivity(), videoList, this::onItemClick);
        recyclerViewMoreVideo.setAdapter(moreVideoAdapter);

        videoOnPreparedListener(videoList.get(1));
    }

    public void videoOnPreparedListener(Video video) {

        String vName = video.getVideoName();
        String vPath = video.getVideoPath();
        videoTitle.setText(vName);

        loadComments(video);
        videoSettingsByDefault();

        String packageName =  getContext().getPackageName();
        String path = "android.resource://" + packageName + "/raw/"+vPath;

        videoView.setVideoURI(Uri.parse(path));
        tv_duration.setText("0:00 / " + video.getVideoDur());
        tv_play.setEnabled(true);
        tv_sound.setEnabled(true);
        videoProgressBar.setVisibility(View.GONE);

//        double dur = (double) videoDuration / 100000;
//        durFormat = String.format("%.2f", dur);
//        tv_duration.setText("0:00 / " + durFormat);

        String hh[] = video.getVideoDur().split(":");
        //0:15
        int timerLimit = Integer.parseInt(hh[0])*60000+Integer.parseInt(hh[1])*1000;

                //15.000 - 10 sec
                //100.000 - 100 sec
                //1.000.000 - 1000 sec

        Log.i("video_path", "timerLimit:"+timerLimit);

        countDownTimer = new CountDownTimer(timerLimit, 1000) {
            public void onTick(long millisUntilFinished) {
                currentDuration++;
                String durText = "";

                Log.i("video_path", "countDownTimer:"+currentDuration);


                if (currentDuration <= 9) durText = "0:0"+currentDuration;
                else if(currentDuration>=60){

                    if((currentDuration%60)>=10) durText = currentDuration/60+":"+(currentDuration%60);
                    else durText = currentDuration/60+":0"+(currentDuration%60);
                }else durText = "0:"+currentDuration;

                tv_duration.setText(durText +" / "+ video.getVideoDur());

            }

            public void onFinish() {
            }
        };

        connectPlayButton();
        connectMuteButton();
    }

    public void connectPlayButton() {
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
    }

    public void connectMuteButton() {
        tv_sound.setOnClickListener(view1 -> {
            if (mute) {
                tv_sound.setBackgroundResource(R.drawable.baseline_volume_off_24);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.setVolume(0f, 0f);
                    }
                });

            } else {
                tv_sound.setBackgroundResource(R.drawable.baseline_volume_up_24);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.setVolume(100f, 100f);
                    }
                });
            }

            mute = !mute;
        });
    }

    public void videoSettingsByDefault(){
        videoProgressBar.setVisibility(View.VISIBLE);
        tv_play.setEnabled(false);
        tv_sound.setEnabled(false);
        currentDuration = 0;
        mute = true;
        playBoolean = true;
        tv_play.setBackgroundResource(R.drawable.baseline_play_arrow_24);
        if(countDownTimer!=null) videoView.stopPlayback();
        if(countDownTimer!=null) countDownTimer.cancel();
    }

    public void loadComments(Video video) {

        commentCount.setText(video.getCommentItemList().size() + " comments");
        commentListAdapter = new CommentListAdapter(getActivity(), video.getCommentItemList());
        recyclerViewComment.setAdapter(commentListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewComment.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewComment.addItemDecoration(dividerItemDecoration);

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.getCommentItemList().add(new CommentItem("Localhost", et_comment.getText().toString()));
                commentListAdapter.notifyDataSetChanged();
                commentCount.setText(video.getCommentItemList().size() + " comments");
                et_comment.setText("");
            }
        });
    }

    public void getDataFromJsonFile() {
        try {
            InputStream is = getActivity().getAssets().open("video_file.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            createModelFromFile(json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createModelFromFile(String json) {
        try {
            JSONObject video = new JSONObject(json);
            JSONArray videoArray = video.getJSONArray("videos");

            for (int i = 0; i < videoArray.length(); i++) {
                JSONObject videoObject = videoArray.getJSONObject(i);

                String videoName = videoObject.getString("videoName");
                String videoPath = videoObject.getString("videoPath");
                String videoDur = videoObject.getString("videoDur");

                JSONArray commentArray = videoObject.getJSONArray("comments");
                List<CommentItem> commentItemList = new ArrayList<>();

                for (int j = 0; j < commentArray.length(); j++) {
                    JSONObject commentObject = commentArray.getJSONObject(j);

                    String from = commentObject.getString("from");
                    String comment = commentObject.getString("comment");

                    commentItemList.add(new CommentItem(from, comment));
                }

                videoList.add(new Video(videoName, videoPath, videoDur, commentItemList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int pos) {
        videoOnPreparedListener(videoList.get(pos));
    }

}
