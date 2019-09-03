package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.dao.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	private List<String> specialFeatures;
	Map<String, List<String>> features;
	FilmDAO filmDAO = new FilmDAOImpl();

	public FilmController() {
		features = new HashMap<>();
		specialFeatures = new ArrayList<String>();
		for (String s : FilmDAOImpl.specialFeatures) {
			specialFeatures.add(s);
		}
		features.put("specialFeatures", specialFeatures);
	}

	@RequestMapping(path = "search.do", method = RequestMethod.GET, params = "filmID")
	public ModelAndView searchByID(int filmID) {
		Film film = filmDAO.getFilmByID(filmID);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		if (film != null) {
			mv.addObject("category", filmDAO.getCategoryOfFilm(film.getId()));
			mv.addObject("actorList", filmDAO.getActors(film.getId()));
		}
		mv.addObject("film", film);
		return mv;
	}

	@RequestMapping(path = "search.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView searchByID(String keyword) {
		List<Film> films = filmDAO.getFilmsByKeyword(keyword);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		mv.addObject("filmList", films);
		return mv;
	}

	@RequestMapping(path = "delete.do", method = RequestMethod.GET, params = "filmID")
	public ModelAndView deleteFilm(int filmID) {
		boolean isDeleted = filmDAO.deleteFilm(filmID);
		ModelAndView mv = new ModelAndView("WEB-INF/completedAction.jsp");
		mv.addObject("isDeleted", isDeleted);
		mv.addObject("filmID", filmID);
		return mv;
	}

	@RequestMapping(path = "add.do", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("WEB-INF/add.jsp");
		mv.addObject("film", new Film());
		mv.addObject("ratings", FilmDAOImpl.ratings);
		mv.addObject("specialFeatures", specialFeatures);
		return mv;
	}

	@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public ModelAndView createFilm(@Valid Film film, Errors errors) {
		if (errors.hasErrors()) {
			return new ModelAndView("WEB-INF/add.jsp").addObject("error", new Boolean(true));
		}
		ModelAndView mv = new ModelAndView("WEB-INF/addresults.jsp");
		mv.addObject("isAdded", filmDAO.addFilm(film));
		return mv;
	}

	@RequestMapping(path = "update.do", method = RequestMethod.GET, params = "filmID")
	public ModelAndView getUpdateInfo(int filmID) {
		ModelAndView mv = new ModelAndView("WEB-INF/update.jsp");
		mv.addObject("film", new Film());
		mv.addObject("filmID", filmID);
		mv.addObject("ratings", FilmDAOImpl.ratings);
		mv.addObject("specialFeatures", specialFeatures);
		return mv;
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView update(@Valid Film film, Errors errors) {
		if (errors.hasErrors()) {
			return new ModelAndView("WEB-INF/update.jsp").addObject("error", new Boolean(true));
		}
		ModelAndView mv = new ModelAndView("WEB-INF/updateresults.jsp");
		mv.addObject("actorList", filmDAO.getActors(film.getId()));
		mv.addObject("film", film);
		mv.addObject("isUpdated", filmDAO.updateFilm(film));
		return mv;
	}

	@RequestMapping(path = "home.do", method = RequestMethod.GET)
	public String goHome() {
		return "index.html";
	}

}
