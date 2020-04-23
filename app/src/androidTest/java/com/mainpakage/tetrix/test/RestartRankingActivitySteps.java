package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import com.mainpakage.tetrix.GameOver;
import com.mainpakage.tetrix.R;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.junit.Rule;

import static junit.framework.Assert.assertNotNull;

//@RunWith(Cucumber.class)
public class RestartRankingActivitySteps {

    @Rule
    public ActivityTestRule<GameOver> activityTestRule = new ActivityTestRule<>(GameOver.class);

    private Activity activity;
    private Button butRestablecer;

    @Before("@restartRanking-feature")
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("Score", "0");
        intent.putExtra("GameMode",-1);
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();
/*
        Intent intent = new Intent(com.mainpakage.tetrix.StartMenu.this, GameOver.class);

        startActivity(intent);*/
    }

    @Given("^I´m in the ranking page$")
    public void im_in_the_ranking_page() {

        assertNotNull(activity);
    }

    @When("^I press the restart ranking button$")
    public void i_press_the_restart_ranking_button() {

        onView(withId(R.id.butRestablecer)).perform(click());

/*
        butRestablecer = (Button) activity.findViewById(R.id.butRestablecer);
        butRestablecer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.ge
                //com.mainpakage.tetrix.GameOver.vaciarRanking(v);
                .vaciarRanking(v);
                return false;
            }
        });*/
        //assertNotNull(activity);
    }

    @Then("^The ranking list is empty$")
    public void the_ranking_list_is_empty() {

        /*
        ViewInteraction listRanking = onView(withId(R.id.listRanking);

        System.out.println(listRanking);
        System.out.println(listRanking.toString().isEmpty());
        */
        //assertNotNull(activity);
    }


}
