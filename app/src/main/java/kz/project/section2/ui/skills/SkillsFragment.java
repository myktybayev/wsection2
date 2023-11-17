package kz.project.section2.ui.skills;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

        getFromJsonFile();
        return view;
    }

    public void getFromJsonFile() {
        try {
            InputStream is = getActivity().getAssets().open("file.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
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