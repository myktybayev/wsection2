package kz.project.section2.ui.photos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kz.project.section2.R;
import kz.project.section2.ui.skills.ItemClick;
import kz.project.section2.ui.skills.SkillItem;
import kz.project.section2.ui.skills.SkillType;
import kz.project.section2.ui.skills.SkillsAdapter;

public class PhotosFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView nFirst, nLast, firstDot, nCenterPre, nCenter, nCenterNext, lastDot;
    PhotosAdapter photosAdapter;
    List<PhotoItem> photoItemListAll = new ArrayList<>();
    List<PhotoItem> photoItemList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        nFirst = view.findViewById(R.id.nFirst);
        firstDot = view.findViewById(R.id.firstDot);
        nCenterPre = view.findViewById(R.id.nCenterPre);
        nCenter = view.findViewById(R.id.nCenter);
        nCenterNext = view.findViewById(R.id.nCenterNext);
        lastDot = view.findViewById(R.id.lastDot);
        nLast = view.findViewById(R.id.nLast);

        /*
        photoItemListAll.add(new PhotoItem(1, " ", "popularity: 230", "visit: 3500"));
        photoItemListAll.add(new PhotoItem(2, " ", "popularity: 330", "visit: 3600"));
        photoItemListAll.add(new PhotoItem(3, " ", "popularity: 430", "visit: 3700"));
        photoItemListAll.add(new PhotoItem(4, " ", "popularity: 530", "visit: 3800"));
        photoItemListAll.add(new PhotoItem(5, " ", "popularity: 630", "visit: 3900"));
        photoItemListAll.add(new PhotoItem(6, " ", "popularity: 630", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(7, " ", "popularity: 700", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(8, " ", "popularity: 710", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(9, " ", "popularity: 720", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(10, " ", "popularity: 730", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(11, " ", "popularity: 740", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(12, " ", "popularity: 750", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(13, " ", "popularity: 13", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(14, " ", "popularity: 14", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(15, " ", "popularity: 15", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(19, " ", "popularity: 19", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(20, " ", "popularity: 20", "visit: 3400"));
        photoItemListAll.add(new PhotoItem(21, " ", "popularity: 21", "visit: 3400"));
        */

        getFromJsonFile();

        photosAdapter = new PhotosAdapter(getActivity(), photoItemList);
        recyclerView.setAdapter(photosAdapter);

        loadData(1);

        navigation();
        return view;
    }

    public void loadData(int page) {
        photoItemList.clear();
        int second = page * 6;//6
        int first = second - 6;//0

        for (PhotoItem photoItem : photoItemListAll) {
            if (photoItem.getPhotoId() > first && photoItem.getPhotoId() <= second) {
                photoItemList.add(photoItem);
            }
        }

        photosAdapter.notifyDataSetChanged();
    }

    public void getFromJsonFile() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("photo_file.json");
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

            for (int i = 0; i < objArray.length(); i++) {
                JSONObject jo_inside = objArray.getJSONObject(i);

                int photoId = jo_inside.getInt("photoId");
                String imagePath = jo_inside.getString("imagePath");
                int popularity = jo_inside.getInt("popularity");
                int visit = jo_inside.getInt("visit");

                photoItemListAll.add(new PhotoItem(photoId, imagePath, "popularity: "+popularity, "visit: "+visit));

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigation() {
        int pageSize = 15;
        nLast.setText("" + pageSize);
        int centerN1 = 4;
        int centerN2 = pageSize - 2;

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

        //page size = 25;
        String tv_texts15[] = {
                " ",//0
                "1,eee,2,3,e,...,15",//1,
                "1,eee,2,3,e,...,15",//2,
                "1,eee,2,3,4,...,15",//3,

                "1,...,3,4,5,...,15",//4,
                "1,...,4,5,6,...,15",//5,
                "1,...,5,6,7,...,15",//6,
                "1,...,6,7,8,...,15",//7,
                "1,...,7,8,9,...,15",//9,
                "1,...,8,9,10,...,15",//9,
                "1,...,9,10,11,...,15",//10,
                "1,...,10,11,12,...,15",//11,
                "1,...,11,12,13,...,15",//12,

                "1,...,12,13,14,eee,15",//13,
                "1,...,e,13,14,eee,15",//14,
                "1,...,e,13,14,eee,15",//15,
        };

        TextView textviews[] = {nFirst, firstDot, nCenterPre, nCenter, nCenterNext, lastDot, nLast};
        View.OnClickListener tv_click2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byDefault();
                String tv_text = ((TextView) view).getText().toString();
                int tv_number = Integer.parseInt(tv_text);


                String curT[] = tv_texts15[tv_number].split(",");

                loadData(tv_number);

                if (tv_number >= centerN1 && tv_number <= centerN2) {
                    nCenter.setBackgroundColor(getActivity().getColor(R.color.grey));
                } else {
                    view.setBackgroundColor(getActivity().getColor(R.color.grey));
                }

                for (int i = 0; i <= 6; i++) {
                    textviews[i].setText(curT[i]);
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
