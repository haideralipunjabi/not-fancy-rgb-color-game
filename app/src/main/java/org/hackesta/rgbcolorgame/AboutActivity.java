package org.hackesta.rgbcolorgame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vansuita.materialabout.builder.AboutBuilder;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout about = findViewById(R.id.layout_about);
        final Context context = this;

        about.addView(AboutBuilder.with(this)
                .setAppIcon(R.drawable.ic_launcher)
                .setAppName(R.string.app_name)
                .setVersionNameAsAppSubTitle()
                .addAction(R.mipmap.github, "Github", getResources().getString(R.string.github_app_url))
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .addFiveStarsAction()
                .setActionsColumnsCount(2)
                .setWrapScrollView(true)
                .setShowAsCard(true).build());

        about.addView(AboutBuilder.with(this)
                .setPhoto(R.drawable.logo_main)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName(R.string.developer)
                .setSubTitle(R.string.subtitle)
                .setBrief(R.string.about)
                .setLinksColumnsCount(4)
                .addLink(R.mipmap.google_play_store, "Play Store", getResources().getString(R.string.play_store_link))
                .addGitHubLink(R.string.developer)
                .addFacebookLink(R.string.developer)
                .addTwitterLink(R.string.twitter_username)
                .addInstagramLink(R.string.developer)
                .addYoutubeChannelLink(R.string.youtube_link)
                .addLink(R.mipmap.email, "Mail", getResources().getString(R.string.email))
                .addLink(R.mipmap.website, "Website",getResources().getString(R.string.website))
                .setWrapScrollView(true)
                .setShowAsCard(true).build());


    }

}
