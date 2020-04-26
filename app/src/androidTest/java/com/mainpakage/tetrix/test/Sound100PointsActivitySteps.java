package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import com.mainpakage.tetrix.CustomView;
import com.mainpakage.tetrix.MainActivity;

import junit.framework.Assert;

import org.junit.Rule;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static junit.framework.TestCase.assertNotNull;

public class Sound100PointsActivitySteps {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Activity activity;
    private CustomView customView;
    public int lines;

    @Before("@sound100Points-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("GameMode",0);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();

    }

    @After("@sound100Points-feature")
    public void tearDown(){
        activityTestRule.finishActivity();
    }

    @Given("^I am in the middle of a game and I want to be alert each 100 points$")
    public void i_am_in_the_middle_of_a_game_and_i_want_to_be_alert_each_100_points(){
        lines = 2;
        assertNotNull(activity);
    }

    @When("^application notifies me$")
    public void application_notifies_me() {
        this.activityTestRule.getActivity().getCustomView().updateScore(lines);
        assertNotNull(activity);
    }

    @Then("^I am motivated$")
    public void i_am_motivated() {
        assertNotNull(activity);
    }

}
