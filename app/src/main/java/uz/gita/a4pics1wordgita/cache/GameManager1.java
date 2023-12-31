package uz.gita.a4pics1wordgita.cache;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import uz.gita.a4pics1wordgita.models.TestData1;

public class GameManager1 {
    private final int MAX_SCORE = 50;
    private final int MIN_SCORE = 10;
    private final int DELTA_SCORE = 5;
    private int currentPosition = 0;
    private int currentLevel = 1;
    private int totalQuestion;
    private int totalTrues = 0;
    private List<TestData1> data;
    private int currentScore = MAX_SCORE;
    private int totalScore = 0;

    public GameManager1(List<TestData1> data) {
        this.data = data;
        totalQuestion = data.size();
        totalScore=100;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    private TestData1 getCurrentData() {
        return data.get(currentPosition);
    }

    public String getCurrentAnswer() {
        return getCurrentData().getAnswer();
    }

    public int getCurrentAnswerLength() {
        return getCurrentAnswer().length();
    }

    public String getCurrentVariant() {
        return getCurrentData().getVariant();
    }

    public ArrayList<Drawable> getCurrentImage() {
        return getCurrentData().getImages();
    }

    public int getCurrentScore() {
        return currentScore;/*MaxScoreCache.getMaxScoreCache().getLastMaxScore();*/
    }

    public int getMaxScore() {
        return MAX_SCORE - MaxScoreCache.getMaxScoreCache().getLastMaxScore();
    }


    public int helpButtonClick(int score) {
        score = ScoreCache1.getScoreCache().getLastScore();
        if (totalScore + ScoreCache1.getScoreCache().getLastScore() >= 10) {
            score = score - MIN_SCORE;
        }
        int smth = getTotalScore() - score;
        return smth;
    }

    public int getCurrentLevel() {
        return currentLevel + LevelCache1.getLevelCache().getLastLevel();
    }

    public boolean checkAnswer(String answer) {
        boolean isTrue = getCurrentAnswer().equalsIgnoreCase(answer);
        if (isTrue) {
            totalScore += currentScore;
            currentScore = MAX_SCORE;
            currentLevel++;
            totalTrues++;
            if (currentPosition + 1 < totalQuestion) {
                currentPosition++;
            }

        } else {
            if (currentScore > MIN_SCORE) {
                currentScore -= DELTA_SCORE;
            }
        }
        return isTrue;
    }

    public int getTotalScore() {
        int totally;
        if (ScoreCache1.getScoreCache().getLastScore() != 1) {
            totally = totalScore + ScoreCache1.getScoreCache().getLastScore();
        } else {
            totalScore = currentScore;
            totally = totalScore;
        }
        return totally;
    }


    public boolean hasData() {
        return currentLevel - 1 < data.size();
    }

}
