package edu.psu.ist.hcdd340.treender;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

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
            new TreeProfile("Santa Cruz lily", "7866", "Oasis Garden & Lotus Pool", R.drawable.santa_cruiz_tree),
            new TreeProfile("Flapjack", "7867", "Arboretum", R.drawable.flapjack_tree),
            new TreeProfile("Japanese Peony", "7868", "Rose & Fragrance Garden", R.drawable.japanese_peony_tree),
            new TreeProfile("Crab Apple", "7869", "Strolling Garden", R.drawable.crabapple_tree),
            new TreeProfile("Black Pine", "7870", "Strolling Garden", R.drawable.blackpine_tree),
            new TreeProfile("Hellebore", "7871", "Strolling Garden", R.drawable.hellebore_tree),
            new TreeProfile("False Spirea", "7872", "Strolling Garden", R.drawable.spirea_tree),
    };

    /**
     * @param menu: top menu bar
     * @return true when id selected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.reset_menu) {
            Log.d(TAG, "Reset icon clicked!");
            View view = findViewById(R.id.rewindIcon);
            updateTreeProfile(getFirstProfile());
            Snackbar.make(view,
                    R.string.snack_bar_reset,
                    Snackbar.LENGTH_SHORT).show();
            return true;
        }
        if (menuId == R.id.about_menu) {
            Log.d(TAG, "About menu clicked!");
            AlertDialog.Builder aboutAlert = new AlertDialog.Builder(this);
            aboutAlert.setTitle(R.string.about_alert_title);
            aboutAlert.setMessage(R.string.about_alert_message);
            aboutAlert.setPositiveButton(android.R.string.ok, null);
            aboutAlert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


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
        // Handle the boost icon
        else if (eventSourceId == R.id.boostIcon) {
            Log.d(TAG, "Boost button clicked!");
        }
        // Handle the like icon
        else if (eventSourceId == R.id.likeIcon) {
            Log.d(TAG, "Like button clicked!");
        }
        // Handle the superlike icon
        else if (eventSourceId == R.id.superLikeIcon) {
            Log.d(TAG, "Superlike button clicked!");
        }
        // Handle the rewind icon
        else if (eventSourceId == R.id.rewindIcon) {
            Log.d(TAG, "Rewind button clicked!");
        }

        // Use || as "or" operator
        if ((eventSourceId == R.id.nopeIcon) || (eventSourceId == R.id.boostIcon)
                || (eventSourceId == R.id.likeIcon) || (eventSourceId == R.id.superLikeIcon)) {
            updateTreeProfile(moveToNextProfile());

            /**
             * Assignment 2 old code - without showTreeProfile function
            // Update TextView contents by getting the fields in Tree Profile objects
            TextView nextTreeID = findViewById(R.id.treeID);
            nextTreeID.setText(getCurrentProfile().getProfileID());

            TextView nextTreeName = findViewById(R.id.treeName);
            nextTreeName.setText(getCurrentProfile().getProfileName());

            TextView nextTreeLocation = findViewById(R.id.locationText);
            nextTreeLocation.setText(getCurrentProfile().getLocation());

            // Update image source by getting image id from current Tree Profile
            ShapeableImageView nextTreeImage = findViewById(R.id.imageTree);
            nextTreeImage.setImageResource(getCurrentProfile().getProfileImageID());
             */
        }
        else if (eventSourceId == R.id.rewindIcon) {
            updateTreeProfile(moveToPreviousProfile());

            /**
             * Assignment 2 old code - without showTreeProfile function
            // Update TextView contents by getting the fields in Tree Profile objects
            TextView nextTreeID = findViewById(R.id.treeID);
            nextTreeID.setText(getCurrentProfile().getProfileID());

            TextView nextTreeName = findViewById(R.id.treeName);
            nextTreeName.setText(getCurrentProfile().getProfileName());

            TextView nextTreeLocation = findViewById(R.id.locationText);
            nextTreeLocation.setText(getCurrentProfile().getLocation());

            // Update image source by getting image id from current Tree Profile
            ShapeableImageView nextTreeImage = findViewById(R.id.imageTree);
            nextTreeImage.setImageResource(getCurrentProfile().getProfileImageID());
            */
        }
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

    /**
     * Gets the first tree profile
     */
    private TreeProfile getFirstProfile() {
        index = 0;
        return TREE_PROFILES[index];
    }

    /**
     * Show a given profile
     */
    private void showTreeProfile(TreeProfile treeProfile) {
        ShapeableImageView treeImage = findViewById(R.id.imageTree);
        treeImage.setImageResource(treeProfile.getProfileImageID());

        TextView view;
        view = findViewById(R.id.treeName);
        view.setText(treeProfile.getProfileName());

        view = findViewById(R.id.treeID);
        view.setText(treeProfile.getProfileID());

        view = findViewById(R.id.locationText);
        view.setText(treeProfile.getLocation());
    }

    /**
     * Update the screen to show the given tree profile
     */
    private void updateTreeProfile(TreeProfile profile) {
        showTreeProfile(profile);
    }
}