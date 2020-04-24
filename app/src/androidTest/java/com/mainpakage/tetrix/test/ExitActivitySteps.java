package com.mainpakage.tetrix.test;


import android.app.Activity;
import android.content.Intent;

import org.junit.Rule;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;




import androidx.test.rule.ActivityTestRule;

import com.mainpakage.tetrix.R;
import com.mainpakage.tetrix.StartMenu;






public class ExitActivitySteps {

    @Rule
    public ActivityTestRule<StartMenu> activityTestRule = new ActivityTestRule<>(StartMenu.class);

    private Activity activity;

    @Before("@exit-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("GameMode",-1);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();

    }

    @After("@exit-feature")
    public void tearDown(){
        activityTestRule.finishActivity();
    }

    @Given("^I'm in the middle of a game and I want to go out$")
    public void im_in_the_middle_of_a_game_and_i_want_to_go_out()
    { assertNotNull(activity);
    }

    @When("^I press the exit button$")
    public void i_press_the_exit_button() {
        onView(withId(R.id.butClassic)).perform(click());
        onView(withId(R.id.butClassic)).perform(click());

    }

    @Then("^I go back to the main menu$")
    public void i_go_back_to_the_main_menu() {
    onView(withId(R.id.exit)).perform(click());
    }
}