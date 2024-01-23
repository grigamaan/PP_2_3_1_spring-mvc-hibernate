package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.CarsDAO;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarsController {

	private final CarsDAO carsDAO;

	@Autowired
	public CarsController(CarsDAO carsDAO) {
		this.carsDAO = carsDAO;
	}

	@GetMapping()
	public String cars(@RequestParam(name = "count", required = false, defaultValue = "5") int count, Model model) {
		List<Car> carList;

		if (count >= 5) {
			carList = carsDAO.index();
		} else {
			carList = carsDAO.index().stream().limit(count).collect(Collectors.toList());
		}

		model.addAttribute("cars", carList);
		return "cars/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("car", carsDAO.show(id));
		return "cars/show";
	}
	
}