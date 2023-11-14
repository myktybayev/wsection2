package kz.project.section2.ui.skills;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;

public class SkillsFragment extends Fragment {

    TextView textView;
    SkillsAdapter skillsAdapter;
    View view;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_skills, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

//        skillItemList.add(new SkillItem("skillItemList 1", "desc1", "R.drawable.image.png"));
//        skillItemList.add(new SkillItem("skillItemList 11", "desc11", "R.drawable.image1.png"));
//        skillItemList.add(new SkillItem("skillItemList 111", "desc111", "R.drawable.image2.png"));
//        skillItemList.add(new SkillItem("skillItemList 1111", "desc1111", "R.drawable.image3.png"));
//        skillItemList.add(new SkillItem("skillItemList 11111", "desc1111", "R.drawable.image1.png"));
//        skillItemList.add(new SkillItem("skillItemList 111111", "desc11111", "R.drawable.image3.png"));
//
//        skillItemList2.add(new SkillItem("skillItemList 21", "desc", "R.drawable.image1.png"));
//        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image2.png"));
//        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image3.png"));
//        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image1.png"));
//        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image.png"));
//        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image3.png"));
//
//        skillTypeArrayList.add(new SkillType("Lesson1", skillItemList));
//        skillTypeArrayList.add(new SkillType("Lesson2", skillItemList2));
//        skillTypeArrayList.add(new SkillType("Lesson3", skillItemList));

        getFromJsonFile();

//        skillsAdapter = new SkillsAdapter(getContext(), skillTypeArrayList);
//        recyclerView.setAdapter(skillsAdapter);


        return view;
    }

    StringBuilder sb = new StringBuilder();

    private void getFromNetwork() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                String url = "https://namaz.muftyat.kz/kk/api/times/2016/50.3/57.166667";
                HttpURLConnection c = null;
                try {
                    URL u = new URL(url);
                    c = (HttpURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.setRequestProperty("Content-Type", "application/json; utf-8");
                    c.setRequestProperty("Accept", "application/json");
                    c.connect();
                    int status = c.getResponseCode();

                    switch (status) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), "utf-8"));

                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line);
                            }

                            createModelFromNetwork(sb.toString());

                            br.close();
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }

    public void createModelFromNetwork(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray result = obj.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {

                JSONObject jo_inside = result.getJSONObject(i);

                String Asr = jo_inside.getString("Asr");
                String Isha = jo_inside.getString("Isha");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getFromJsonFile() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("file.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            createModelFromFile(json);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createModelFromFile(String json) {
        try {
            JSONArray objArray = new JSONArray(json);

            List<SkillType> skillTypeArrayList = new ArrayList<>();

            for (int i = 0; i < objArray.length(); i++) {

                JSONObject jo_inside = objArray.getJSONObject(i);

                String parentTitle = jo_inside.getString("title");

                JSONArray jo_insideArray = jo_inside.getJSONArray("array");

                List<SkillItem> skillItemList = new ArrayList<>();

                for (int j = 0; j < jo_insideArray.length(); j++) {
                    JSONObject jo_array_inside = jo_insideArray.getJSONObject(j);

                    String title = jo_array_inside.getString("title");
                    String desc = jo_array_inside.getString("desc");
                    String imagePath = jo_array_inside.getString("imagePath");

                    skillItemList.add(new SkillItem(title, desc, imagePath));
                }

                skillTypeArrayList.add(new SkillType(parentTitle, skillItemList));

            }

            skillsAdapter = new SkillsAdapter(getContext(), skillTypeArrayList);
            recyclerView.setAdapter(skillsAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}