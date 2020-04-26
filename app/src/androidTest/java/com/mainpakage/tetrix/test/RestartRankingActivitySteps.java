package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.rule.ActivityTestRule;
import com.mainpakage.tetrix.GameOver;
import com.mainpakage.tetrix.R;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import org.junit.Rule;
import java.util.Timer;
import java.util.TimerTask;

public class RestartRankingActivitySteps {

    @Rule
    public ActivityTestRule<GameOver> activityTestRule = new ActivityTestRule<>(GameOver.class);

    private Activity activity;
    private Button butRestablecer;
    private GameOver gameOver;

    @Before("@restartRanking-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("Score", "0");
        intent.putExtra("GameMode",-2);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();
        this.gameOver = new GameOver();

    }
    @After("@restartRanking-feature")
    public void tearDown(){
        activityTestRule.finishActivity();
    }

    @Given("^I´m in the ranking page$")
    public void im_in_the_ranking_page() {
        onView(withId(R.id.playerName)).perform(typeText(""), closeSoftKeyboard());

    }

    @When("^I press the restart ranking button$")
    public void i_press_the_restart_ranking_button() {

        onView(withId(R.id.butRestablecer)).perform(click());
        gameOver.updateRanking();

    }

    @Then("^The ranking list is empty$")
    public void the_ranking_list_is_empty() {

        gameOver.isRankingVacio();

    }


}
