package kz.project.section2.ui.photos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;
import kz.project.section2.ui.skills.ItemClick;
import kz.project.section2.ui.skills.SkillItem;
import kz.project.section2.ui.skills.SkillType;
import kz.project.section2.ui.skills.SkillsAdapter;

public class PhotosFragment extends Fragment{
    LinearLayout linearTvs;
    View view;
    RecyclerView recyclerView;
    TextView nFirst, nLast, firstDot, nCenterPre, nCenter, nCenterNext, lastDot;
    PhotosAdapter photosAdapter;
    List<PhotoItem> photoItemList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        linearTvs = view.findViewById(R.id.linearTvs);

        nFirst = view.findViewById(R.id.nFirst);
        firstDot = view.findViewById(R.id.firstDot);
        nCenterPre = view.findViewById(R.id.nCenterPre);
        nCenter = view.findViewById(R.id.nCenter);
        nCenterNext = view.findViewById(R.id.nCenterNext);
        lastDot = view.findViewById(R.id.lastDot);
        nLast = view.findViewById(R.id.nLast);

        photoItemList.add(new PhotoItem(" ", "popularity", "visit"));
        photoItemList.add(new PhotoItem(" ", "popularity", "visit"));
        photoItemList.add(new PhotoItem(" ", "popularity", "visit"));

        photosAdapter = new PhotosAdapter(getContext(), photoItemList);
        recyclerView.setAdapter(photosAdapter);

        navigation();
        return view;
    }

    public void navigation(){
        int pageSize = 20;
        nLast.setText(""+pageSize);
        int centerN1 = 4;
        int centerN2 = pageSize-2;

        String tv_texts[] = {
                " ",//0
                "1,eee,2,3,e,...,10",//1,
                "1,eee,2,3,e,...,10",//2,
                "1,eee,2,3,4,...,10",//3,

                "1,...,3,4,5,...,10",//4,
                "1,...,4,5,6,...,10",//5,
                "1,...,5,6,7,...,10",//6,
                "1,...,6,7,8,...,10",//7,

                "1,...,7,8,9,eee,10",//8,
                "1,...,e,8,9,eee,10",//9,
                "1,...,e,8,9,eee,10"//10,
        };

        //page size = 20;
        String tv_texts1[] = {
                " ",//0
                "1,eee,2,3,e,...,20",//1,
                "1,eee,2,3,e,...,20",//2,
                "1,eee,2,3,4,...,20",//3,

                "1,...,3,4,5,...,20",//4,
                "1,...,4,5,6,...,20",//5,
                "1,...,5,6,7,...,20",//6,
                "1,...,6,7,8,...,20",//7,
                "1,...,7,8,9,...,20",//9,
                "1,...,8,9,10,...,20",//9,
                "1,...,9,10,11,...,20",//10,
                "1,...,10,11,12,...,20",//11,
                "1,...,11,12,13,...,20",//12,
                "1,...,12,13,14,...,20",//13,
                "1,...,13,14,15,...,20",//14,
                "1,...,14,15,16,...,20",//15,
                "1,...,15,16,17,...,20",//16,
                "1,...,16,17,18,...,20",//17,

                "1,...,17,18,19,eee,20",//18,
                "1,...,e,18,19,eee,20",//19,
                "1,...,e,18,19,eee,20",//20,
        };

        TextView textviews[] = {nFirst, firstDot, nCenterPre, nCenter, nCenterNext, lastDot, nLast};
        View.OnClickListener tv_click2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byDefault();
                String tv_text = ((TextView) view).getText().toString();
                int tv_number = Integer.parseInt(tv_text);
                String curT[] = tv_texts1[tv_number].split(",");

                if(tv_number>=centerN1 && tv_number<=centerN2){
                    nCenter.setBackgroundColor(getActivity().getColor(R.color.grey));
                }else{
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));
                }

                for (int i = 0; i <= 6; i++) {
                    textviews[i].setText(curT[i]);
                    Log.i("tv_click2", "tv_click2: "+curT[i]);
                    if (curT[i].contains("e")) {
                        textviews[i].setVisibility(View.GONE);
                    }
                }
            }
        };

        nFirst.setOnClickListener(tv_click2);
        nCenterPre.setOnClickListener(tv_click2);
        nCenter.setOnClickListener(tv_click2);
        nCenterNext.setOnClickListener(tv_click2);
        nLast.setOnClickListener(tv_click2);
    }

    public void byDefault() {
        nFirst.setVisibility(View.VISIBLE);
        firstDot.setVisibility(View.VISIBLE);
        nCenterPre.setVisibility(View.VISIBLE);
        nCenter.setVisibility(View.VISIBLE);
        nCenterNext.setVisibility(View.VISIBLE);
        lastDot.setVisibility(View.VISIBLE);
        nLast.setVisibility(View.VISIBLE);

        nFirst.setBackgroundColor(getActivity().getColor(R.color.white));
        nCenterPre.setBackgroundColor(getActivity().getColor(R.color.white));
        nCenter.setBackgroundColor(getActivity().getColor(R.color.white));
        nCenterNext.setBackgroundColor(getActivity().getColor(R.color.white));
        nLast.setBackgroundColor(getActivity().getColor(R.color.white));

    }

}

/*

        View.OnClickListener tv_click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byDefault();
                String tv_text = ((TextView) view).getText().toString();
                int tv_number = Integer.parseInt(tv_text);

                if (tv_number == 1) {
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));

                    firstDot.setVisibility(View.GONE);
                    lastDot.setVisibility(View.VISIBLE);

                    nCenterPre.setVisibility(View.VISIBLE);
                    nCenter.setVisibility(View.VISIBLE);
                    nCenterNext.setVisibility(View.GONE);

                    nCenterPre.setText("" + (tv_number + 1));
                    nCenter.setText("" + (tv_number + 2));

                } else if (tv_number == 2) {
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));

                    firstDot.setVisibility(View.GONE);
                    lastDot.setVisibility(View.VISIBLE);

                    nCenterPre.setVisibility(View.VISIBLE);
                    nCenter.setVisibility(View.VISIBLE);
                    nCenterNext.setVisibility(View.GONE);

                    nCenterPre.setText("" + (tv_number));
                    nCenter.setText("" + (tv_number + 1));

                } else if (tv_number == 3) {

                    firstDot.setVisibility(View.GONE);
                    lastDot.setVisibility(View.VISIBLE);

                    nCenterPre.setVisibility(View.VISIBLE);
                    nCenterNext.setVisibility(View.VISIBLE);

                    nCenterPre.setText("" + (tv_number - 1));
                    nCenter.setText("" + (tv_number));
                    nCenterNext.setText("" + (tv_number + 1));
                    nCenter.setBackgroundColor(getActivity().getColor(R.color.grey));

                } else if (tv_number >= 4 && tv_number <= 7) {
                    firstDot.setVisibility(View.VISIBLE);
                    lastDot.setVisibility(View.VISIBLE);

                    nCenterPre.setText("" + (tv_number - 1));
                    nCenter.setText("" + tv_number);
                    nCenter.setBackgroundColor(getActivity().getColor(R.color.grey));
                    nCenterNext.setText("" + (tv_number + 1));

                } else if (tv_number == 8) {
                    lastDot.setVisibility(View.GONE);
                    nCenterPre.setVisibility(View.VISIBLE);

                    nCenterPre.setText("" + (tv_number - 1));
                    nCenter.setText("" + tv_number);
                    nCenter.setBackgroundColor(getActivity().getColor(R.color.grey));
                    nCenterNext.setText("" + (tv_number + 1));

                } else if (tv_number == 9) {
                    lastDot.setVisibility(View.GONE);
                    nCenterPre.setVisibility(View.GONE);

                    nCenterNext.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));

                } else if (tv_number == 10) {
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));

                    firstDot.setVisibility(View.VISIBLE);
                    lastDot.setVisibility(View.GONE);
                    nCenterPre.setVisibility(View.GONE);

                    nCenter.setVisibility(View.VISIBLE);
                    nCenterNext.setVisibility(View.VISIBLE);

                    nCenter.setText("" + (tv_number - 2));
                    nCenterNext.setText("" + (tv_number - 1));
                }
            }
        };
 */