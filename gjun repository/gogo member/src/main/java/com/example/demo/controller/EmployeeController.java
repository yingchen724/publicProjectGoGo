package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;

import java.util.*;

@RestController
/*@CrossOrigin(origins = "http://127.0.0.1:5500")*/
@CrossOrigin /* 跨網域存取時需要這個annotation */
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	EmployeeRepository respository;
	@Autowired
	Employee emp;
	/* 列出所有emp */
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployee(ModelMap model) {		
		return respository.findAll();
	}
	 
	/* 修改emp */
	 @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee x) 
	  {
		 System.out.println("update employees id "+id);
		 System.out.println("employee  "+ x);
	      List<Employee>  data=respository.findAll();
	      
//	      int index=-1;
//	      for(int i=0;i<data.size();i++) {
//	    	  if(data.get(i).getId()==id) {
//	    		  index=i;
//	    		  break;
//	    	  }
//	      }
//	      if(index>=0) {
//	    	  data.set(index, employee);
//	    	  return new ResponseEntity<Employee>(employee, HttpStatus.OK);
//	      }
	      
	      /* lambda寫法 */
	      Employee em=data.stream().filter(e->e.getId()==id).findFirst().orElse(null);
	      
	      if(em==null)
	    	  return new ResponseEntity<Employee>(x, HttpStatus.NOT_FOUND);
	      else {
	    	  em.setUsername(x.getUsername());
		      em.setPassword(x.getPassword());
		      em.setEmail(x.getEmail());
		      
		      respository.save(em);
		      return new ResponseEntity<Employee>(x, HttpStatus.OK);
	      }
	    	  
	  }
	   
	 /* 新增emp */
	 @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<String> addEmployee(@RequestBody Employee emp) 
	  {
		List<Employee> data=respository.findAll();
		Employee ex=data.stream().max((c1,c2)-> c1.getId()-c2.getId()).get();
		emp.setId(ex.getId()+1);		
		respository.save(emp);
	    return new ResponseEntity<String>(HttpStatus.CREATED);
	  }
	 
	  /* 刪除 */
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) 
	  {
		List<Employee>  data=respository.findAll();
	    Employee ex=data.stream().filter(e->e.getId()==id).findFirst().get();
		if(ex!=null) {
			respository.deleteById(ex.getId());
	       return new ResponseEntity<String>(HttpStatus.OK);
		}else	   
	      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	  }

	  @RequestMapping(value="/emplogin",method=RequestMethod.GET)
	  @ResponseBody
	  public String empLogin(@RequestParam String username,@RequestParam String password) {
		  if(username.equals(emp.getUsername())&password.equals(emp.getPassword())) {
			  return "login success";
		  }
		  else {
			  return "login failed";
		  }
		  
		  
	  }

}

	   



