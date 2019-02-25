package com.example.a.criminalintent;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.criminalintent.model.Crime;
import com.example.a.criminalintent.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

import static com.example.a.criminalintent.DatePickerFragment.EXTRA_DATE;


public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "ARG_CRIME_ID";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSuspectButton;
    private Button mReportButton;

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;

    public static CrimeFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimId);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog
                        = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, null);
            }
        });

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        final Intent pickIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        mSuspectButton = v.findViewById(R.id.crime_suspect_text);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickIntent, REQUEST_CONTACT);
            }
        });
        PackageManager packageManager = getActivity().getPackageManager();
        if(packageManager.resolveActivity(pickIntent,
                PackageManager.MATCH_DEFAULT_ONLY) == null){
            mSuspectButton.setEnabled(false);
        }

        mReportButton = v.findViewById(R.id.crime_report_text);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getReport());
                intent.putExtra(Intent.EXTRA_SUBJECT, "범죄 보고서");
                startActivity(intent);
            }
        });
        return v;
    }

    public String getReport(){
        String solvedString = mCrime.isSolved() ? "해결됨" : "미해결";
        String suspect = mCrime.getSuspect();
        if(suspect == null)
            suspect = "용의자 없음";

        return solvedString+" "+mCrime.getDate().toString()+" "+suspect;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_DATE && resultCode == Activity.RESULT_OK){
            Date date = (Date) data.getSerializableExtra(EXTRA_DATE);
            mDateButton.setText(date.toString());
            mCrime.setDate(date);
        }
        if(requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK){
            Uri contactUri = data.getData();

            String[] queryFields = new String[]
                    {ContactsContract.Contacts.DISPLAY_NAME};

            Cursor c = getActivity().getContentResolver()
                    .query(contactUri, queryFields,
                            null, null, null);
            if(c.getCount() == 0)
                return;
            c.moveToFirst();
            String suspect = c.getString(0);
            c.close();
            mCrime.setSuspect(suspect);
            mSuspectButton.setText(suspect);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_delete_crime){
            CrimeLab.get(getActivity()).deleteCrime(mCrime);
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
