package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import org.junit.Rule;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import androidx.test.rule.ActivityTestRule;
import com.mainpakage.tetrix.R;
import com.mainpakage.tetrix.StartMenu;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import com.mainpakage.tetrix.GameOver;

public class RankingActivitySteps {

    @Rule
    public ActivityTestRule<StartMenu> activityTestRule = new ActivityTestRule<>(StartMenu.class);

    private Activity activity;

    @Before("@ranking-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("GameMode",-1);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();

    }
    @After("@ranking-feature")
    public void tearDown(){
        activityTestRule.finishActivity();
    }

    @Given("^I´m in the main menu of game$")
    public void im_in_the_main_menu_of_game() {

        assertNotNull(activity);
    }

    @When("^I press the show ranking button$")
    public void i_press_the_show_ranking_button() {

        onView(withId(R.id.butRanking)).perform(click());

    }

    @Then("^I can see the best scores of the players$")
    public void i_can_see_the_best_scores_of_the_players() {

        activityTestRule.finishActivity();
    }

}
