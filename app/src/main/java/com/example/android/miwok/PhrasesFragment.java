/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = "PhrasesFragment";
    private MediaPlayer mMediaPlayer;
    final ArrayList<Word> words = new ArrayList<Word>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_list, container, false);

        //Create a list of words
        words.add(new Word("Where are you going?\n" +
                "\n", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?\n" +
                "\n", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...\n" +
                "\n", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?\n" +
                "\n", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.\n" +
                "\n", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?\n" +
                "\n", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.\n" +
                "\n", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.\n" +
                "\n", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.\n" +
                "\n", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.\n" +
                "\n", "әnni'nem", R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create layouts for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = view.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        releaseMediaPlayer();

        Word current = words.get(position);

        Log.d(TAG, "onItemClick: " + current);

        mMediaPlayer = MediaPlayer.create(getActivity(), current.getAudioResourceId());
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't be playing any more sounds
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaPlayer();
    }
}