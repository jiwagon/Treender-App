package edu.psu.ist.hcdd340.treender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

/**
 * Data class representing a tree profile
 */
final class TreeProfile {
    private final String profileName;
    private final String profileID;
    private final String location;
    private final int profileImageID;

    TreeProfile(String profileName, String profileID, String location, int profileImageID) {
        this.profileName = profileName;
        this.profileID = profileID;
        this.location = location;
        this.profileImageID = profileImageID;
    }

    public int getProfileImageID() {
        return profileImageID;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileID() {
        return profileID;
    }

    public String getLocation() {
        return location;
    }
}

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int[] ACTION_ICON_IDS = {
            R.id.rewindIcon,
            R.id.nopeIcon,
            R.id.boostIcon,
            R.id.likeIcon,
            R.id.superLikeIcon
    };

    private static final String TAG = "TREENDER_ACTIVITY";

    private static final TreeProfile[] TREE_PROFILES = {
            new TreeProfile("Hosler Oak", "7863", "Arboretum", R.drawable.hosler_tree),
            new TreeProfile("Banana", "7864", "Esplanade in Arboretum", R.drawable.banana_tree),
            new TreeProfile("Hemlock", "7865", "Entry Walk in Arboretum", R.drawable.hemlock_tree),
            /*
            new TreeProfile("Santa Cruz lily", "7866", "Oasis Garden & Lotus Pool", R.drawable.santa_cruiz_tree),
            new TreeProfile("Flapjack", "7867", "Arboretum", R.drawable.flapjack_tree),
            new TreeProfile("Japanese Peony", "7868", "Rose & Fragrance Garden", R.drawable.japanese_peony_tree),
            new TreeProfile("Crab Apple", "7869", "Strolling Garden", R.drawable.crabapple_tree),
            new TreeProfile("Black Pine", "7870", "Strolling Garden", R.drawable.blackpine_tree),
            new TreeProfile("Hellebore", "7871", "Strolling Garden", R.drawable.hellebore_tree),
            new TreeProfile("False Spirea", "7872", "Strolling Garden", R.drawable.spirea_tree), */
    };

    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Set event handler for icons
         */
        ShapeableImageView nope = findViewById(R.id.nopeIcon);
        nope.setOnClickListener(this); //Attach a listener with the event source

        ShapeableImageView boost = findViewById(R.id.boostIcon);
        boost.setOnClickListener(this);

        ShapeableImageView like = findViewById(R.id.likeIcon);
        like.setOnClickListener(this);

        ShapeableImageView superlike = findViewById(R.id.superLikeIcon);
        superlike.setOnClickListener(this);

        ShapeableImageView rewind = findViewById(R.id.rewindIcon);
        rewind.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        /*
        Handle onClick events
         */
        int eventSourceId = view.getId();
        //Log.d(TAG, String.format("event source id: %s", view.getId()));

        // Handle the nope icon
        if (eventSourceId == R.id.nopeIcon) {
            Log.d(TAG, "Nope button clicked!");
        }
        else if (eventSourceId == R.id.boostIcon) {
            Log.d(TAG, "Boost button clicked!");
        }
        else if (eventSourceId == R.id.likeIcon) {
            Log.d(TAG, "Like button clicked!");
        }
        else if (eventSourceId == R.id.superLikeIcon) {
            Log.d(TAG, "Superlike button clicked!");
        }
        else if (eventSourceId == R.id.rewindIcon) {
            Log.d(TAG, "Rewind button clicked!");
        }

        TextView idNumberTextView = findViewById(R.id.treeID);
        String currentProfileId = idNumberTextView.getText().toString();
        Log.d(TAG, "Current profile id: " + currentProfileId);
    }


    /**
     * Gets next tree profile by increasing the index by 1. Also, saves the index.
     */
    private TreeProfile moveToNextProfile() {
        index = (index + 1) % TREE_PROFILES.length;
        return TREE_PROFILES[index];
    }

    /**
     * Gets previous tree profile by decreasing the index by 1. Also, saves the index.
     */
    private TreeProfile moveToPreviousProfile() {
        index = index - 1;
        if (index < 0)
            index = TREE_PROFILES.length - 1;
        return TREE_PROFILES[index];
    }


    /**
     * Gets current profile
     */
    private TreeProfile getCurrentProfile() {
        return TREE_PROFILES[index];
    }


}