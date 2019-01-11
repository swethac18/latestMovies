package swetha.popularmoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.posterImageView)
    ImageView mPosterImageView;
    @BindView(R.id.titleTextView)
    TextView mOriginalTitleTextView;
    @BindView(R.id.releaseDateTextView)
    TextView mReleaseDateTextView;
    @BindView(R.id.voteAvgTextView)
    TextView mVoteAvgTextView;
    @BindView(R.id.overviewTextView)
    TextView mOverViewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if(getIntent().hasExtra(Intent.EXTRA_TEXT)){
            String[] details = getIntent().getStringArrayExtra(Intent.EXTRA_TEXT);
            Picasso.with(this)
                    .load(details[0])
                    .error(R.drawable.errorimage)
                    .placeholder(R.drawable.placeholder)
                    .into(mPosterImageView);
            mOriginalTitleTextView.setText("\n"+getString(R.string.titleLabel)+" "+details[1]+"\n");
            mReleaseDateTextView.setText(getString(R.string.releaseDateLabel)+" "+details[2]+"\n");
            mVoteAvgTextView.setText(getString(R.string.voteAvgLabel)+" "+details[3]+"\n");
            mOverViewTextView.setText(getString(R.string.plotSynopsisLabel)+" "+details[4]);
        }
    }
}
