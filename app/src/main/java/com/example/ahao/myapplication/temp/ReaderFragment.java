package com.example.ahao.myapplication.temp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahao.myapplication.R;

/**
 * Created by Ahao on 2019/3/3
 */
public class ReaderFragment extends Fragment {
    private ReaderView readerView;
    private int which;
    private TextView title;
    private TextView percent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novel_reader_layout, container, false);
        Toolbar toolbar=view.findViewById(R.id.toolbar2);
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_like:

                        break;
                }
                return true;
            }
        });

        readerView=view.findViewById(R.id.reader);
        title=view.findViewById(R.id.title);
        percent=view.findViewById(R.id.percent);
        readerView.setShowAtListener(new ReaderView.ShowAtListener() {
            @Override
            public void showAt(int pos) {
                ((MainActivityTemp) getActivity()).setTxt(which,pos);
               /* Bundle bundle = new Bundle();
                bundle.putInt("showat",pos);
                ReaderFragment.this.setArguments(bundle);*/

            }
        });
        return view;
    }

    public static ReaderFragment newInstance(int pos) {
        ReaderFragment fragment = new ReaderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("which",pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            which = args.getInt("which");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String tx =((MainActivityTemp)getActivity()).getTxt(which);
        readerView.setTxt(tx);
        if(which==0)
            ((MainActivityTemp)getActivity()).initFirstPage();
    }

    public void updateArguments(int pos) {
        which = pos;
        Bundle bundleArgs = getArguments();
        if (bundleArgs != null) {
            bundleArgs.putInt("which", pos);
        }
    }

    public void setReaderTxt(String title, String per)
    {
        String tx =((MainActivityTemp)getActivity()).getTxt(which);
        this.title.setText(title);
        percent.setText("本章进度："+per);
        readerView.setTxt(tx);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

}
