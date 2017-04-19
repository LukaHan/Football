package com.football.football;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lukahan on 2016/9/6.
 */
public class BaseFragment extends Fragment {
    public String TAG = "cmlog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void myStartActivity(Class cls, boolean needFinish) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        if (needFinish) getActivity().finish();
    }

    public void myStartActivity(Class cls) {
        myStartActivity(cls, false);
    }

    public void myStartActivity(Intent intent) {
        startActivity(intent);
    }

    public void myStartActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
//        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
