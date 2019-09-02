package com.skilldistillery.film.controllers;


import java.util.List;

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
	FilmDAO filmDAO = new FilmDAOImpl();
	
	@RequestMapping(path = "search.do", method = RequestMethod.GET, params= "filmID")
	public ModelAndView searchByID(int filmID) {
		Film film = filmDAO.getFilmByID(filmID);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		mv.addObject("film", film);
		return mv;
	}
	
	@RequestMapping(path = "search.do", method = RequestMethod.GET, params= "keyword")
	public ModelAndView searchByID(String keyword) {
		List<Film> films = filmDAO.getFilmsByKeyword(keyword);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		mv.addObject("filmList", films);
		return mv;
	}
	
	@RequestMapping(path = "delete.do", method = RequestMethod.GET, params="filmID")
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
		return mv;
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public String createFilm(@Valid Film film, Errors errors) {
		if (errors.hasErrors()) {
			return "WEB-INF/add.jsp";
		}
		filmDAO.addFilm(film);
		return "WEB-INF/addsuccess.jsp";
	}
	

}
