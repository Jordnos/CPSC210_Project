# My Personal Project

## Intro

This will be an application that will allow the user to choose from a list of **brain games/tests** to
play. There will also be a **leaderboard system** in place for each of the different games. To use the
leaderboard, there will be an account system, with unique usernames for each account. The people who
will use this application are those who are looking for a simple and short game to play or people who
enjoy competing with their friends or other people to get a high score.

I've played many games that are extremely fun, but looks very simple from the outside. I find these 
games very interesting, and I hope to be able to create such a game in the future. 
*[humanbenchmark](https://humanbenchmark.com/)* is a website with many examples of these games; this 
website is an inspiration for this project as I will attempt to replicate some of these games. 

## Phase 1 User Stories

- As a user, I want to be able to create an account and add it to the account list.
- As a user, I want to be able to log in and out of my account.
- As a user, I want to be able to change menus (main, games, leaderboards, etc).
- As a user, I want to be able to select a game to play, and play it.
- As a user, I want to be able to record my score onto the leaderboard.  

## Phase 2 User Stories

- As a user, I want to be able to save the account list and leaderboard to a file.
- As a user, I want to be able to load an account list and leaderboard from a file.  

## Phase 3 User Stories

- As a user, I want to be able to change passwords of my account.
- As a user, I want to be able to delete my account from the account list and scores.  

## Phase 4: Task 2

Fri Apr 01 03:00:53 PDT 2022  
New User Created - User: testUser Password: 12345  
Fri Apr 01 03:00:58 PDT 2022  
Password Changed - User: testUserNew password: 123  
Fri Apr 01 03:01:10 PDT 2022  
Account Deleted - User: testUser  
Fri Apr 01 03:01:17 PDT 2022  
New User Created - User: userTest2 Password: 123  
Fri Apr 01 03:01:27 PDT 2022  
New Score Added To Leaderboard - User: userTest2 Score: 1  
Fri Apr 01 03:01:43 PDT 2022  
Score Removed From Leaderboard - User: userTest2 Score: 1  
Fri Apr 01 03:01:43 PDT 2022  
New Score Added To Leaderboard - User: userTest2 Score: 3

## Phase 4: Task 3

- make classes for each pane in the main menu
- an abstract class extended by the multiple frame classes
- an abstract class extended by SignupFrame and LoginFrame, for user input boxes 
- an abstract class extended by AccountListFrame and LeaderboardFrame, for making a list
- take Singleton Design Pattern from EventLog and apply it to AccountList and Leaderboard


