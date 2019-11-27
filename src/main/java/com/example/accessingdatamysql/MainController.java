package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class MainController {
	private int ids=1;
	private final AtomicLong tempCounter = new AtomicLong();
	private int answer = new Random().nextInt(1 + 5);
	private ArrayList<Guess> guessModelList = new ArrayList<Guess>();

	@Autowired
	private GuessRepository guessRepository;


	@GetMapping("/guess")
	public String guessForm(Model model, HttpSession session) {
		model.addAttribute("guess", new Guess());
		Integer SessionInt = 0;
		session.setAttribute("sessionInt", SessionInt);
		return "guess";
	}

	@PostMapping("/guess")
	public String guessSubmit(@ModelAttribute Guess guess, HttpSession session)
	{
		Integer SessionInt = (Integer) session.getAttribute("sessionInt");
		if (SessionInt == null) {
			SessionInt = 0;
			session.setAttribute("sessionInt", SessionInt);
		}

		// Modify session integer
		SessionInt = SessionInt + 1;
		session.setAttribute("sessionInt", SessionInt);

		session.setAttribute("sessionGuess", guess);
		session.setAttribute("sessionCounter", tempCounter);
		session.setAttribute("guessModelList", guessModelList); //guess model list for session user

		int userGuess = guess.getGuessNum();

		guess.setErrorCounter(SessionInt);
		guess.setId(ids);
		guess.setGuessNum(userGuess);


		if (userGuess == answer)
		{
			guess.setWinOrLose(1);
//			guessModelList.add(guess);
			guessRepository.save(guess); // save in sql db

			answer = new Random().nextInt(1 + 5); // new number for next game
			ids++;

			return "guessResult";
		}
		return "guess";
	}

//	@GetMapping("/all")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userRepository.findAll();
//	}

	@GetMapping("/guesses")
	public @ResponseBody Iterable<Guess> getAllGuesses() {
		// This returns a JSON or XML with the users
		return guessRepository.findAll();
	}
}
