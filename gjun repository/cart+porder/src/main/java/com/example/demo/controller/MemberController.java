package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
	
public class MemberController {
	
@Autowired
MemberRepository repo;
@Autowired
HttpServletResponse response;
List<Member> data;
	@PostMapping("/addmember")
    public ResponseEntity<?> createMember(@RequestBody Member m) {
		
		Member me=new Member();
		me.setName(m.getName());
		me.setEmail(m.getEmail());
		me.setAddress(m.getAddress());
		me.setMobile(m.getMobile());
		me.setPassword(m.getPassword());
    if(repo.existsByEmail(m.getEmail())) {
    	return  new ResponseEntity<>("email already exist",HttpStatus.OK);
    }
    else if(m.getEmail()==""||m.getName()=="") {
    	return  new ResponseEntity<>("field cannot be null",HttpStatus.OK);
    }
    else { 
      repo.save(m);
      return new ResponseEntity<>("regist success",HttpStatus.CREATED);
         }
    }
	
	@RequestMapping(value="/findmember",method=RequestMethod.GET)
	public List<Member> getAllMember(){
	 return repo.findAll();
	}
	/*@RequestMapping(value="/findbyemail/{email}",method=RequestMethod.GET)
	public Member findbyemail(HttpSession session,@PathVariable("email")String email){
		String attrEmail=(String) session.getAttribute("attrEmail");
		data=repo.findAll();
			
		Member m=data.stream().filter(e->e.getEmail().equals(attrEmail)).findFirst().get();
		
		return m;
		
	}*/
	@RequestMapping(value="/findbyemail",method=RequestMethod.GET)
	public Member findbyemail(HttpSession session){
		String attrEmail=(String) session.getAttribute("attrEmail");
		data=repo.findAll();
			
		Member m=data.stream().filter(e->e.getEmail().equals(attrEmail)).findFirst().get();
		
		return m;
		
	}
	
	
	  @PostMapping("/updatemember")
	    public ResponseEntity<?> updateMember(HttpSession session, @RequestBody Member m) {
		String attrEmail=(String)session.getAttribute("attrEmail");
		Member me=repo.findByEmail(attrEmail);
		  //data=repo.findAll();
		  
		  //Member me=data.stream().filter(e->e.getEmail().equals(attrEmail)).findFirst().orElse(null);
   	   			
			
				me.setEmail(attrEmail);
   	   			me.setPassword(m.getPassword());
   	   			me.setAddress(m.getAddress());
   	   			me.setMobile(m.getMobile());
		  System.out.println(me);
		  
		  repo.save(me);
		  return new ResponseEntity<>("update success",HttpStatus.OK);
		  
	    	/*if(me!=null) {  
	    	 
	           repo.save(me);
	    	   return new ResponseEntity<>(m, HttpStatus.OK);
	    	}
	    	else {
	          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email not exist");
	    }*/
	  }		  
	  @RequestMapping(value= "/{email}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteEmployee(@PathVariable("email")String email){
	  	List<Member> data=repo.findAll();
	  	Member m=data.stream().filter(e->e.getEmail().equals(email)).findFirst().get();
	  	if(m!=null) {
	  		repo.delete(m);
	  		return new ResponseEntity<String>(HttpStatus.OK);
	  	}
	  	else {
	  		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	  	}
	  }
	  
	  @RequestMapping(value="/memlogin",method=RequestMethod.POST)
	  @ResponseBody
	  public String MemLogin(@RequestBody Member m,HttpSession session) throws IOException {
		session.setAttribute("attrEmail", m.getEmail());  
		String email=m.getEmail();
		String password=m.getPassword();
		Member member=repo.findByEmail(email);
		if(member!=null && password.equals(member.getPassword())) {
			
			return "success.html";
		}
		else {
			return"account not exist";
		}
	  }
	  
	  
	  
}
