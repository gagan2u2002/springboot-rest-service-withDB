package com.etechon.restful.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etechon.restful.model.Employee;
import com.etechon.restful.service.EmployeeService;

/**
 * 
 * @author Gagan.Tyagi 
 * At this time this is a dummy controller which is used to
 *         insert/update/delete/select in PostGres database
 * 
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;

	/***
	 * Method is used to Add Employee record , every time is a new record added
	 * with new id.
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		empService.save(employee);
		logger.debug("Added:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	/***
	 * Method is used to update employee record based on user ID
	 * http://localhost:8080/rest/employee/
	 * 
	 * @param employee
	 * @return
	 */

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		Employee existingEmp = empService.getById(employee.getId());
		if (existingEmp == null) {
			logger.debug("Employee with id " + employee.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.save(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	/***
	 * Method is used to select employee based on its id's
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Employee:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/***
	 * Method is used to select all employee id's
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = empService.getAll();
		if (employees.isEmpty()) {
			logger.debug("Employees does not exists");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + employees.size() + " Employees");
		logger.debug(Arrays.toString(employees.toArray()));
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	/***
	 * Method is used to Delete employee record based on Id
	 * http://localhost:8080/rest/employee/1
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.delete(id);
			logger.debug("Employee with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
