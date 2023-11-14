package kz.project.section2.ui.skills;

import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;

public class SkillsFragment extends Fragment{

    TextView textView;
    SkillsAdapter skillsAdapter;
    List<SkillType> skillTypeArrayList = new ArrayList<>();
    List<SkillItem> skillItemList = new ArrayList<>();
    List<SkillItem> skillItemList2 = new ArrayList<>();
    View view;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_skills, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        skillItemList.add(new SkillItem("skillItemList 1", "desc1", "R.drawable.image.png"));
        skillItemList.add(new SkillItem("skillItemList 11", "desc11", "R.drawable.image1.png"));
        skillItemList.add(new SkillItem("skillItemList 111", "desc111", "R.drawable.image2.png"));
        skillItemList.add(new SkillItem("skillItemList 1111", "desc1111", "R.drawable.image3.png"));
        skillItemList.add(new SkillItem("skillItemList 11111", "desc1111", "R.drawable.image1.png"));
        skillItemList.add(new SkillItem("skillItemList 111111", "desc11111", "R.drawable.image3.png"));

        skillItemList2.add(new SkillItem("skillItemList 21", "desc", "R.drawable.image1.png"));
        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image2.png"));
        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image3.png"));
        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image1.png"));
        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image.png"));
        skillItemList2.add(new SkillItem("skillItemList 22", "desc", "R.drawable.image3.png"));

        skillTypeArrayList.add(new SkillType("Lesson1", skillItemList));
        skillTypeArrayList.add(new SkillType("Lesson2", skillItemList2));
        skillTypeArrayList.add(new SkillType("Lesson3", skillItemList));

        skillsAdapter = new SkillsAdapter(getContext(), skillTypeArrayList);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(skillsAdapter);

        return view;
    }
}