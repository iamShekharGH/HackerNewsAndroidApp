package com.iamshekhargh.hackernews;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.iamshekhargh.hackernews.customElements.CustomProgressDialog;
import com.iamshekhargh.hackernews.customElements.CustomTextView;
import com.iamshekhargh.hackernews.datastore.DataStoreProvider;
import com.iamshekhargh.hackernews.datastore.GetStoryIdsDatastore;
import com.iamshekhargh.hackernews.datastore.interfaces.GetStoryIds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GetStoryIds.Interactor {

    private static final String TAG = "MainActivity";
    CustomProgressDialog customProgressDialog;
    ProgressDialog progressDialog;
    GetStoryIds getStoryIds;

//    int[] list = {
//            14363395, 14364268, 14361039, 14364612, 14361717, 14360729, 14363639, 14362756, 14365151, 14363414,
//            14361425, 14363424, 14362398, 14357568, 14362975, 14360921, 14364516, 14361791, 14362539, 14359159,
//            14362329, 14355590, 14365425, 14355743, 14359058, 14355720, 14339308, 14361427, 14364674, 14360549,
//    };

    int[] list = {
            14363395, 14364268, 14361039, 14364612, 14361717, 14360729, 14363639, 14362756, 14365151, 14363414,
            14361425, 14363424, 14362398, 14357568, 14362975, 14360921, 14364516, 14361791, 14362539, 14359159,
            14362329, 14355590, 14365425, 14355743, 14359058, 14355720, 14339308, 14361427, 14364674, 14360549,
            14353357, 14353853, 14351324, 14359630, 14364061, 14365178, 14363450, 14364307, 14361348, 14350705,
            14360314, 14350293, 14363101, 14361522, 14358429, 14360073, 14361206, 14356930, 14364425, 14353748,
            14356409, 14354177, 14364573, 14358639, 14357881, 14353717, 14357761, 14357986, 14363029, 14359563,
            14344034, 14350129, 14358629, 14359312, 14357379, 14364412, 14350059, 14354911, 14352187, 14350148,
            14355051, 14363379, 14363268, 14363977, 14359901, 14349964, 14354843, 14330547, 14361718, 14356188,
            14359634, 14352431, 14351976, 14355081, 14351485, 14362903, 14343967, 14351436, 14355080, 14364137,
            14356408, 14362621, 14344347, 14359052, 14360029, 14357136, 14358120, 14348859, 14361635, 14343058,
            14359920, 14352592, 14360405, 14346227, 14359990, 14363308, 14344644, 14351260, 14346593, 14362946,
            14343787, 14352463, 14348442, 14343790, 14343358, 14358186, 14334819, 14350396, 14332006, 14349218,
            14347315, 14347092, 14360997, 14346652, 14339293, 14347758, 14360071, 14347908, 14347415, 14347100,
            14323265, 14347648, 14354424, 14350965, 14347521, 14350523, 14335931, 14352521, 14349322, 14349711,
            14362730, 14353290, 14350848, 14352137, 14347663, 14335261, 14340796, 14351008, 14336093, 14351829,
            14354400, 14362422, 14337275, 14354073, 14347694, 14352386, 14346536, 14349147, 14338948, 14360699,
            14352568, 14343766, 14334845, 14354828, 14335506, 14356377, 14351711, 14362748, 14344114, 14347497,
            14348382, 14349064, 14347211, 14362751, 14338708, 14338328, 14362620, 14344060, 14351463, 14320392,
            14343409, 14340035, 14333396, 14355964, 14354819, 14353727, 14335378, 14342521, 14347188, 14352298,
            14338411, 14347055, 14331994, 14321044, 14326512, 14360471, 14347288, 14347957, 14332179, 14340896,
            14338212, 14357289, 14352248, 14337817, 14348725, 14354698, 14352139, 14334154, 14350485, 14352453,
            14345605, 14333882, 14334715, 14362696, 14318406, 14331752, 14358957, 14337870, 14346484, 14347010,
            14341160, 14353939, 14332257, 14334455, 14349190, 14332479, 14353921, 14342905, 14333957, 14331727,
            14326439, 14355170, 14348042, 14340137, 14332741, 14349664, 14337569, 14346721, 14355310, 14318518,
            14363657, 14338908, 14336803, 14336360, 14335313, 14341623, 14342567, 14337871, 14359341, 14348097,
            14333157, 14330932, 14342156, 14352835, 14335310, 14345438, 14358773, 14321577, 14345505, 14328397,
            14352466, 14332206, 14341845, 14336666, 14337854, 14330989, 14349880, 14358276, 14357666, 14344881,
            14347106, 14335212, 14357710, 14333031, 14335496, 14353595, 14343396, 14327891, 14333425, 14349934,
            14354293, 14335159, 14321213, 14331180, 14322524, 14343772, 14361390, 14329931, 14335014, 14357667,
            14329167, 14321174, 14330654, 14332159, 14340286, 14336951, 14318700, 14334746, 14336563, 14328650,
            14345317, 14354567, 14351997, 14356548, 14343722, 14329482, 14353101, 14334230, 14359283, 14347090,
            14349113, 14317893, 14356044, 14343389, 14344715, 14332606, 14330635, 14344937, 14329877, 14318663,
            14354869, 14346698, 14321498, 14336508, 14353739, 14323747, 14321088, 14326896, 14326166, 14335513,
            14330842, 14334710, 14338413, 14353628, 14338111, 14358736, 14352945, 14345670, 14320675, 14356626,
            14323454, 14322980, 14323218, 14352637, 14329914, 14340905, 14340106, 14351726, 14319922, 14330389,
            14336052, 14356471, 14322093, 14338386, 14321455, 14330784, 14320973, 14330088, 14332221, 14338967,
            14334371, 14345314, 14333421, 14319543, 14328237, 14347605, 14321381, 14354136, 14347546, 14317971,
            14328388, 14325061, 14324138, 14329563, 14354768, 14330820, 14358159, 14326913, 14341880, 14325855,
            14321013, 14338252, 14350094, 14337718, 14318646, 14335208, 14322377, 14331027, 14346718, 14322607,
            14322953, 14320701, 14329137, 14334114, 14331295, 14353984, 14349455, 14344293, 14341967, 14325702,
            14328124, 14330284, 14331306, 14320013, 14331942, 14325040, 14335194, 14334742, 14334607, 14325487,
            14338861, 14319460, 14321914, 14332333, 14323406, 14322664, 14335286, 14321638, 14329515, 14337609,
            14329210, 14349831, 14323338, 14330034, 14323128, 14329548, 14326408, 14326505, 14329835, 14346356,
            14328274, 14340560, 14323575, 14338737, 14324013, 14333272, 14327624, 14323762, 14346145, 14323143,
            14323238, 14335398, 14323148, 14323533, 14336918, 14320339, 14326358, 14334750, 14326363, 14329496,
            14331914, 14333395, 14335023, 14341972, 14334221, 14354292, 14328600, 14325316, 14336047, 14336695,
            14328346, 14318754, 14341405, 14319938, 14324129, 14327238, 14322476, 14334372, 14336293, 14347023,
            14338779, 14353242, 14342229, 14336067, 14333594, 14326416, 14333829, 14352565, 14352527, 14324379,
            14330885, 14351085, 14330200, 14318345, 14344733, 14327372, 14322546, 14330215, 14351667, 14333790,
            14325532, 14329294, 14329964, 14330626, 14330873, 14325045, 14331410, 14329953, 14349202, 14337788
    };


    String[] listArray;
    @BindView(R.id.textView2)
    CustomTextView textView2;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.main_Layout)
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Uncomment it for testing.

//        listArray = news String[list.length];
//        convertToStringArray();
//        startNavActivity(giveme10(4));

        getStoryIds = DataStoreProvider.getStoryListIds(this);
        getStoryIds.getStoryIDs();

    }

    //For testing.
    private void convertToStringArray() {
        for (int i = 0; i < list.length; i++) {
            listArray[i] = list[i] + "";
        }
    }

    //For testing.
    private String[] giveme10(int num) {
        String[] temp = new String[num];
        if (num > list.length) {
            convertToStringArray();
            return listArray;
        } else {
            for (int i = 0; i < num; i++) {
                temp[i] = list[i] + "";
            }
            return temp;
        }
    }


    private void startNavActivity(String[] strings) {
        NavDrawerActivity.startNavDrawerActivity(this, strings);
        finish();
    }

//    Class Helper functions.
//  ---------------------------------------------------->

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showProgressBar() {
        if (customProgressDialog != null) {
            customProgressDialog.show();
        }
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    private void hideProgressBar() {
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

//  <----------------------------------------------------


//    GetStoryIds.Interactor methods
//  ---------------------------------------------------->

    @Override
    public void onDataArrived(String[] list) {
        startNavActivity(list);
    }


    @Override
    public void onError() {
        Snackbar snackbar = Snackbar.make(mainLayout, "Cannot Connect at the moment.", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getStoryIds.getStoryIDs();
                    }
                });
        snackbar.show();

    }

//  <----------------------------------------------------

}
