package com.mainpakage.tetrix.test;

import android.app.Activity;
import android.content.Intent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import androidx.test.rule.ActivityTestRule;
import com.mainpakage.tetrix.StartMenu;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.not;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import com.mainpakage.tetrix.GameOver;
import com.mainpakage.tetrix.StartMenu;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RankingActivitySteps {

    @Rule
    public ActivityTestRule<StartMenu> activityTestRule = new ActivityTestRule<>(StartMenu.class);

    private Activity activity;

    @Before("@ranking-feature")
    public void setup(){
/*
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();
*/
        /*Intent intent = new Intent(com.mainpakage.tetrix.StartMenu.this, GameOver.class);

        startActivity(intent);*/
    }


    @Given("^I´m in the main menu of game$")
    public void im_in_the_main_menu_of_game() throws Throwable {

        throw new PendingException();
    }

    @When("^I press the show ranking button$")
    public void i_press_the_show_ranking_button() throws Throwable {
        throw new PendingException();
    }

    @Then("^I can see the best scores of the players$")
    public void i_can_see_the_best_scores_of_the_players() throws Throwable {
        throw new PendingException();
    }

}
