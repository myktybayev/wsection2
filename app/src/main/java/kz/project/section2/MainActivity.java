package kz.project.section2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kz.project.section2.ui.photos.PhotosFragment;
import kz.project.section2.ui.skills.SkillsFragment;
import kz.project.section2.ui.videos.VideoFragment;

public class MainActivity extends AppCompatActivity {
    Button btnHome, btnSkills, btnPhotos, btnVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.btnHome);
        btnSkills = findViewById(R.id.btnSkills);
        btnPhotos = findViewById(R.id.btnPhotos);
        btnVideos = findViewById(R.id.btnVideos);

        switchFragment(new SkillsFragment());

        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byDefault();

                if (view.getId() == R.id.btnHome) {
                    switchFragment(new PhotosFragment());
                    btnHome.setBackground(getDrawable(R.drawable.border_grey));
                }

                if (view.getId() == R.id.btnSkills) {
                    switchFragment(new SkillsFragment());

                    btnSkills.setBackground(getDrawable(R.drawable.border_grey));
                }

                if (view.getId() == R.id.btnPhotos) {
                    switchFragment(new PhotosFragment());
                    btnPhotos.setBackground(getDrawable(R.drawable.border_grey));

                }

                if (view.getId() == R.id.btnVideos) {
                    switchFragment(new VideoFragment());

                    btnVideos.setBackground(getDrawable(R.drawable.border_grey));
                }

            }
        };

        btnHome.setOnClickListener(btnClick);
        btnSkills.setOnClickListener(btnClick);
        btnPhotos.setOnClickListener(btnClick);
        btnVideos.setOnClickListener(btnClick);

    }

    public void byDefault() {
        btnHome.setBackground(getDrawable(R.drawable.border));
        btnSkills.setBackground(getDrawable(R.drawable.border));
        btnPhotos.setBackground(getDrawable(R.drawable.border));
        btnVideos.setBackground(getDrawable(R.drawable.border));
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null); // Optional: add to back stack
        transaction.commit();
    }
}