package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Guess
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private int guessNum;

    private int winOrLose;

    private int guessCounter;

    public int getGuessNum() {
        return guessNum;
    }

    public int getId() {
        return id;
    }

    public int getWinOrLose() {
        return winOrLose;
    }

    public int getGuessCounter()
    {
        return guessCounter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWinOrLose(int winOrLose) {
        this.winOrLose = winOrLose;
    }

    public void setErrorCounter(int guessCounter)
    {
        this.guessCounter = guessCounter;
    }


    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

//    @Override
//    public String toString() {
//        return "Guess{" +
//                "id=" + id +
//                ", guessNum=" + guessNum +
//                ", winOrLose=" + winOrLose +
//                ", guessCounter=" + guessCounter +
//                '}';
//    }
}
