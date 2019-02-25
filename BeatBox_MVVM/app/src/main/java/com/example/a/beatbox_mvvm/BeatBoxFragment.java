package com.example.a.beatbox_mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.beatbox_mvvm.databinding.FragmentBeatBoxBinding;
import com.example.a.beatbox_mvvm.databinding.ListItemSoundBinding;

public class BeatBoxFragment extends Fragment {
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager(
                new GridLayoutManager(getActivity(), 3) );
        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
