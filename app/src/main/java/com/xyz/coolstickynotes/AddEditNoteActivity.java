package com.xyz.coolstickynotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout relativeLayoutSt;
    private EditText editTextTitle;
    private EditText editTextDescription;

    ImageButton buttonDone, buttonColor, buttonSticker, buttonFont, buttonSize;
    FloatingActionButton buttonEdit;

    SeekBar seekBarTextSize;
    LinearLayout linearLayoutSeekbar, linearLayoutTools;

    HorizontalScrollView hsvColor, hsvSticker, hsvFont;
    ImageView noteColor1, noteColor2, noteColor3, noteColor4, noteColor5, noteColor6, noteColor7, noteColor8, noteColor9, noteColor10, noteColor11, noteColor12, noteColor13, noteColor14, noteColor15,
            imageSticker, noteSticker1, noteSticker2, noteSticker3, noteSticker4, noteSticker5, noteSticker6, noteSticker7, noteSticker8, noteSticker9, noteSticker10;

    Typeface typeface1, typeface2, typeface3, typeface4, typeface5, typeface6, typeface7, typeface8, typeface9, typeface10;
    TextView font1, font2, font3, font4, font5, font6, font7, font8, font9, font10;

    int noteColor = 0, noteSticker = 0, noteFont = 0;
    float textSize;


    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_COLOR = "EXTRA_COLOR";
    public static final String EXTRA_STICKER = "EXTRA_STICKER";
    public static final String EXTRA_FONT = "EXTRA_FONT";
    public static final String EXTRA_FONT_SIZE = "EXTRA_FONT_SIZE";


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        initViews();


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));

            linearLayoutTools.setVisibility(View.GONE);

        } else {
            setTitle("Add Note");
            buttonEdit.setVisibility(View.GONE);
        }


        seekBarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {

                    if (!editTextTitle.hasFocus()) {//-------------------------------------------

                        editTextDescription.setTextSize(progress);
                        textSize = progress;
                    }

                }
            }
        });
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = new SimpleDateFormat(" d MMM yyyy, HH:mm",Locale.getDefault()).format(Calendar.getInstance().getTime());


        if (description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        if(title.equals("")){
            if(description.length()>=12)
            data.putExtra(EXTRA_TITLE, description.substring(0,12)+"...");
            else
                data.putExtra(EXTRA_TITLE, description);
        }else {
            data.putExtra(EXTRA_TITLE, title);
        }
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_COLOR,noteColor);
        data.putExtra(EXTRA_STICKER,noteSticker);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initViews() {

        relativeLayoutSt = findViewById(R.id.relativeSticky);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextText);
        imageSticker = findViewById(R.id.imageSticker);

        buttonColor = findViewById(R.id.btnColor);
        buttonSticker = findViewById(R.id.btnSticker);
        buttonFont = findViewById(R.id.btnFont);
        buttonDone = findViewById(R.id.btnDone);
        buttonSize = findViewById(R.id.btnSize);
        buttonEdit = findViewById(R.id.btnEdit);

        hsvColor = findViewById(R.id.horizontalColor);
        hsvFont = findViewById(R.id.horizontalFont);
        hsvSticker = findViewById(R.id.horizontalStickers);

        hsvColor.setVisibility(View.GONE);
        hsvFont.setVisibility(View.GONE);
        hsvSticker.setVisibility(View.GONE);

        buttonColor.setOnClickListener(this);
        buttonSticker.setOnClickListener(this);
        buttonFont.setOnClickListener(this);
        buttonSize.setOnClickListener(this);
        buttonDone.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);


        noteColor1 = findViewById(R.id.NoteColor1);
        noteColor2 = findViewById(R.id.NoteColor2);
        noteColor3 = findViewById(R.id.NoteColor3);
        noteColor4 = findViewById(R.id.NoteColor4);
        noteColor5 = findViewById(R.id.NoteColor5);
        noteColor6 = findViewById(R.id.NoteColor6);
        noteColor7 = findViewById(R.id.NoteColor7);
        noteColor8 = findViewById(R.id.NoteColor8);
        noteColor9 = findViewById(R.id.NoteColor9);
        noteColor10 = findViewById(R.id.NoteColor10);
        noteColor11 = findViewById(R.id.NoteColor11);
        noteColor12 = findViewById(R.id.NoteColor12);
        noteColor13 = findViewById(R.id.NoteColor13);
        noteColor14 = findViewById(R.id.NoteColor14);
        noteColor15 = findViewById(R.id.NoteColor15);


        noteColor1.setOnClickListener(this);
        noteColor2.setOnClickListener(this);
        noteColor3.setOnClickListener(this);
        noteColor4.setOnClickListener(this);
        noteColor5.setOnClickListener(this);
        noteColor6.setOnClickListener(this);
        noteColor7.setOnClickListener(this);
        noteColor8.setOnClickListener(this);
        noteColor9.setOnClickListener(this);
        noteColor10.setOnClickListener(this);
        noteColor11.setOnClickListener(this);
        noteColor12.setOnClickListener(this);
        noteColor13.setOnClickListener(this);
        noteColor14.setOnClickListener(this);
        noteColor15.setOnClickListener(this);

        imageSticker = findViewById(R.id.imageSticker);
        noteSticker1 = findViewById(R.id.NoteSticker1);
        noteSticker2 = findViewById(R.id.NoteSticker2);
        noteSticker3 = findViewById(R.id.NoteSticker3);
        noteSticker4 = findViewById(R.id.NoteSticker4);
        noteSticker5 = findViewById(R.id.NoteSticker5);
        noteSticker6 = findViewById(R.id.NoteSticker6);
        noteSticker7 = findViewById(R.id.NoteSticker7);
        noteSticker8 = findViewById(R.id.NoteSticker8);
        noteSticker9 = findViewById(R.id.NoteSticker9);
        noteSticker10 = findViewById(R.id.NoteSticker10);

        noteSticker1.setOnClickListener(this);
        noteSticker2.setOnClickListener(this);
        noteSticker3.setOnClickListener(this);
        noteSticker4.setOnClickListener(this);
        noteSticker5.setOnClickListener(this);
        noteSticker6.setOnClickListener(this);
        noteSticker7.setOnClickListener(this);
        noteSticker8.setOnClickListener(this);
        noteSticker9.setOnClickListener(this);
        noteSticker10.setOnClickListener(this);

        font1 = findViewById(R.id.NoteFont1);
        font2 = findViewById(R.id.NoteFont2);
        font3 = findViewById(R.id.NoteFont3);
        font4 = findViewById(R.id.NoteFont4);
        font5 = findViewById(R.id.NoteFont5);
        font6 = findViewById(R.id.NoteFont6);
        font7 = findViewById(R.id.NoteFont7);
        font8 = findViewById(R.id.NoteFont8);
        font9 = findViewById(R.id.NoteFont9);
        font10 = findViewById(R.id.NoteFont10);

        font1.setOnClickListener(this);
        font2.setOnClickListener(this);
        font3.setOnClickListener(this);
        font4.setOnClickListener(this);
        font5.setOnClickListener(this);
        font6.setOnClickListener(this);
        font7.setOnClickListener(this);
        font8.setOnClickListener(this);
        font9.setOnClickListener(this);
        font10.setOnClickListener(this);

        linearLayoutSeekbar = (LinearLayout) findViewById(R.id.linearLayoutSeekbar);
        linearLayoutSeekbar.setVisibility(View.GONE);
        linearLayoutTools = findViewById(R.id.linearTools);


        seekBarTextSize = findViewById(R.id.seekbarTexrSize);

        setupFonts();

    }


    public void setupFonts() {

        typeface1 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
        typeface3 = Typeface.createFromAsset(getAssets(), "fonts/font3.ttf");
        typeface4 = Typeface.createFromAsset(getAssets(), "fonts/font4.ttf");
        typeface5 = Typeface.createFromAsset(getAssets(), "fonts/font5.ttf");
        typeface6 = Typeface.createFromAsset(getAssets(), "fonts/font6.ttf");
        typeface7 = Typeface.createFromAsset(getAssets(), "fonts/font7.ttf");
        typeface8 = Typeface.createFromAsset(getAssets(), "fonts/font8.ttf");
        typeface9 = Typeface.createFromAsset(getAssets(), "fonts/font9.ttf");
        typeface10 = Typeface.createFromAsset(getAssets(), "fonts/font10.ttf");

        font1.setTypeface(typeface1);
        font2.setTypeface(typeface2);
        font3.setTypeface(typeface3);
        font4.setTypeface(typeface4);
        font5.setTypeface(typeface5);
        font6.setTypeface(typeface6);
        font7.setTypeface(typeface7);
        font8.setTypeface(typeface8);
        font9.setTypeface(typeface9);
        font10.setTypeface(typeface10);
    }


    private void getNoteSticker(int noteSticker) {
        this.noteSticker=noteSticker;
        imageSticker.setBackgroundResource(noteSticker);
    }

    private void getNoteColor(int noteColor) {
        this.noteColor=noteColor;
        relativeLayoutSt.setBackgroundResource(noteColor);

//        if (noteColor == 1) {
//            relativeLayoutSt.setBackgroundResource(R.color.note1);
//        } else if (noteColor == 2) {
//            relativeLayoutSt.setBackgroundResource(R.color.note2);
//        } else if (noteColor == 3) {
//            relativeLayoutSt.setBackgroundResource(R.color.note3);
//        } else if (noteColor == 4) {
//            relativeLayoutSt.setBackgroundResource(R.color.note4);
//        } else if (noteColor == 5) {
//            relativeLayoutSt.setBackgroundResource(R.color.note5);
//        } else if (noteColor == 6) {
//            relativeLayoutSt.setBackgroundResource(R.color.note6);
//        } else if (noteColor == 7) {
//            relativeLayoutSt.setBackgroundResource(R.color.note7);
//        } else if (noteColor == 8) {
//            relativeLayoutSt.setBackgroundResource(R.color.note8);
//        } else if (noteColor == 9) {
//            relativeLayoutSt.setBackgroundResource(R.color.note9);
//        } else if (noteColor == 10) {
//            relativeLayoutSt.setBackgroundResource(R.color.note10);
//        } else if (noteColor == 11) {
//            relativeLayoutSt.setBackgroundResource(R.color.note11);
//        } else if (noteColor == 12) {
//            relativeLayoutSt.setBackgroundResource(R.color.note12);
//        } else if (noteColor == 13) {
//            relativeLayoutSt.setBackgroundResource(R.color.note13);
//        } else if (noteColor == 14) {
//            relativeLayoutSt.setBackgroundResource(R.color.note14);
//        } else if (noteColor == 15) {
//            relativeLayoutSt.setBackgroundResource(R.color.note15);
//        }
    }


    private void getNoteFont(int noteFont) {
        if (noteFont == 1) {
            editTextTitle.setTypeface(typeface1);
            editTextDescription.setTypeface(typeface1);
        } else if (noteFont == 2) {
            editTextTitle.setTypeface(typeface2);
            editTextDescription.setTypeface(typeface2);
        } else if (noteFont == 3) {
            editTextTitle.setTypeface(typeface3);
            editTextDescription.setTypeface(typeface3);
        } else if (noteFont == 4) {
            editTextTitle.setTypeface(typeface4);
            editTextDescription.setTypeface(typeface4);
        } else if (noteFont == 5) {
            editTextTitle.setTypeface(typeface5);
            editTextDescription.setTypeface(typeface5);
        } else if (noteFont == 6) {
            editTextTitle.setTypeface(typeface6);
            editTextDescription.setTypeface(typeface6);
        } else if (noteFont == 7) {
            editTextTitle.setTypeface(typeface7);
            editTextDescription.setTypeface(typeface7);
        } else if (noteFont == 8) {
            editTextTitle.setTypeface(typeface8);
            editTextDescription.setTypeface(typeface8);
        } else if (noteFont == 9) {
            editTextTitle.setTypeface(typeface9);
            editTextDescription.setTypeface(typeface9);
        } else if (noteFont == 10) {
            editTextTitle.setTypeface(typeface10);
            editTextDescription.setTypeface(typeface10);
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnDone:

                if (editTextTitle.getText().toString().trim().equals("") && editTextDescription.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Write something", Toast.LENGTH_SHORT).show();
                } else {
                    saveNote();
                }

                break;

            case R.id.btnColor:
                hsvSticker.setVisibility(View.GONE);
                hsvFont.setVisibility(View.GONE);
                hsvColor.setVisibility(View.VISIBLE);
                linearLayoutSeekbar.setVisibility(View.GONE);
                break;

            case R.id.btnSticker:
                hsvSticker.setVisibility(View.VISIBLE);
                hsvColor.setVisibility(View.GONE);
                hsvFont.setVisibility(View.GONE);
                linearLayoutSeekbar.setVisibility(View.GONE);
                break;

            case R.id.btnFont:
                hsvSticker.setVisibility(View.GONE);
                hsvColor.setVisibility(View.GONE);
                hsvFont.setVisibility(View.VISIBLE);
                linearLayoutSeekbar.setVisibility(View.GONE);
                break;

            case R.id.btnSize:

                if (editTextTitle.hasFocus()) {
                    Toast.makeText(getApplicationContext(), "Title size can't increased", Toast.LENGTH_SHORT).show();
                } else {
                    hsvSticker.setVisibility(View.GONE);
                    hsvColor.setVisibility(View.GONE);
                    hsvFont.setVisibility(View.GONE);
                    linearLayoutSeekbar.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.btnEdit:
                linearLayoutTools.setVisibility(View.VISIBLE);
                buttonEdit.setVisibility(View.GONE);

                editTextTitle.setFocusableInTouchMode(true);
                editTextDescription.setFocusableInTouchMode(true);

//                editTextSt.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                break;


            case R.id.NoteColor1:
                getNoteColor(R.color.notepad1);
                break;

            case R.id.NoteColor2:
                getNoteColor(R.color.notepad2);
                break;

            case R.id.NoteColor3:
                getNoteColor(R.color.notepad3);
                break;

            case R.id.NoteColor4:
                getNoteColor(R.color.notepad4);
                break;

            case R.id.NoteColor5:
                getNoteColor(R.color.notepad5);
                break;

            case R.id.NoteColor6:
                getNoteColor(R.color.notepad6);
                break;

            case R.id.NoteColor7:
                getNoteColor(R.color.notepad7);
                break;

            case R.id.NoteColor8:
                getNoteColor(R.color.notepad8);
                break;

            case R.id.NoteColor9:
                getNoteColor(R.color.notepad9);
                break;

            case R.id.NoteColor10:
                getNoteColor(R.color.notepad10);
                break;

            case R.id.NoteColor11:
                getNoteColor(R.color.notepad11);
                break;

            case R.id.NoteColor12:
                getNoteColor(R.color.notepad12);
                break;

            case R.id.NoteColor13:
                getNoteColor(R.color.notepad13);
                break;

            case R.id.NoteColor14:
                getNoteColor(R.color.notepad14);
                break;

            case R.id.NoteColor15:
                getNoteColor(R.color.notepad15);
                break;


            case R.id.NoteSticker1:
                getNoteSticker(R.drawable.sticker1);
                break;

            case R.id.NoteSticker2:
                getNoteSticker(R.drawable.sticker2);
                break;

            case R.id.NoteSticker3:
                getNoteSticker(R.drawable.sticker3);
                break;

            case R.id.NoteSticker4:
                getNoteSticker(R.drawable.sticker4);
                break;

            case R.id.NoteSticker5:
                getNoteSticker(R.drawable.sticker5);
                break;

            case R.id.NoteSticker6:
                getNoteSticker(R.drawable.sticker6);
                break;

            case R.id.NoteSticker7:
                getNoteSticker(R.drawable.sticker7);
                break;

            case R.id.NoteSticker8:
                getNoteSticker(R.drawable.sticker8);
                break;

            case R.id.NoteSticker9:
                getNoteSticker(R.drawable.sticker9);
                break;

            case R.id.NoteSticker10:
                getNoteSticker(R.drawable.sticker10);
                break;


            case R.id.NoteFont1:
                getNoteFont(1);
                noteFont = 1;
                break;

            case R.id.NoteFont2:
                getNoteFont(2);
                noteFont = 2;
                break;

            case R.id.NoteFont3:
                getNoteFont(3);
                noteFont = 3;
                break;

            case R.id.NoteFont4:
                getNoteFont(4);
                noteFont = 4;
                break;

            case R.id.NoteFont5:
                getNoteFont(5);
                noteFont = 5;
                break;

            case R.id.NoteFont6:
                getNoteFont(6);
                noteFont = 6;
                break;

            case R.id.NoteFont7:
                getNoteFont(7);
                noteFont = 7;
                break;

            case R.id.NoteFont8:
                getNoteFont(8);
                noteFont = 8;
                break;

            case R.id.NoteFont9:
                getNoteFont(9);
                noteFont = 9;
                break;

            case R.id.NoteFont10:
                getNoteFont(10);
                noteFont = 10;
                break;
        }
    }
}
