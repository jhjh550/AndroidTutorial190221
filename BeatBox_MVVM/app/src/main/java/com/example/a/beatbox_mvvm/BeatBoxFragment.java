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
import com.example.a.beatbox_mvvm.model.BeatBox;
import com.example.a.beatbox_mvvm.model.Sound;
import com.example.a.beatbox_mvvm.model.SoundViewModel;

import java.util.List;

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager(
                new GridLayoutManager(getActivity(), 3) );
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }
        public void bind(Sound sound){
            mBinding.getViewModel().setSound(sound);
        }
    }
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;


        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ListItemSoundBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(getActivity()),
                    R.layout.list_item_sound,
                    viewGroup, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder viewHolder, int i) {
            viewHolder.bind(mSounds.get(i));
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
