package com.greenrecyclebin.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends ActionBarActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.greenrecyclebin.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.greenrecyclebin.android.geoquiz.answer_show";

    private static final String KEY_ANSWER_SHOWN = "mIsAnswerShown";

    private boolean mAnswerIsTrue;
    private boolean mIsAnswerShown;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        if (getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWN, false) || savedInstanceState != null && savedInstanceState.getBoolean(KEY_ANSWER_SHOWN))
            setAnswerText();
        else
            setAnswerShownResult(false);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText();
            }
        });
    }

    private void setAnswerText() {
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        if (mAnswerIsTrue) mAnswerTextView.setText(R.string.true_button);
        else mAnswerTextView.setText(R.string.false_button);

        setAnswerShownResult(true);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        mIsAnswerShown = isAnswerShown;

        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_ANSWER_SHOWN, mIsAnswerShown);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cheat, container, false);
            return rootView;
        }
    }

}
