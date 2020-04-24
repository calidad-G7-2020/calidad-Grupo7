package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.rule.ActivityTestRule;

import com.mainpakage.tetrix.MainActivity;
import com.mainpakage.tetrix.StartMenu;
import com.mainpakage.tetrix.R;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.junit.Rule;

import static junit.framework.Assert.assertNotNull;


public class BackGroundActivitySteps {

    @Rule
    public ActivityTestRule<StartMenu> activityTestRule = new ActivityTestRule<>(StartMenu.class);

    private Activity activity;

    @Before("@backGround-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("GameMode",0);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();

    }

    @After("@backGround-feature")
    public void tearDown(){

        activityTestRule.finishActivity();
    }

    @Given("^I'm in the menu of the game and I want to change background color$")
    public void im_in_the_menu_of_the_game_and_i_want_to_change_background_color() {
        assertNotNull(activity);
    }

    @When("^I select the background in select bar$")
    public void i_select_the_background_in_select_bar() {
        onView(withId(R.id.buttonNex)).perform(click());
        onView(withId(R.id.buttonNex)).perform(click());
    }

    @Then("^I change the background color$")
    public void i_change_the_background_color() {
        onView(withId(R.id.butClassic)).perform(click());

   /*     onView(withId(R.id.butClassic)).perform(click());

        for(int i =0 ; i<20; i++) {
            onView(withId(R.id.flechabajo)).perform(click());
        }

        activity.getApplicationContext().getApplicationContext() ;
        activity.getApplication();
        activity.finish();
        */
    }

}