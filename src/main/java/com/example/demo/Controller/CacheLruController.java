package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CacheLruService;

@RestController
@RequestMapping("/cacheLRU")
public class CacheLruController {

	@Autowired
	private CacheLruService CacheLrtService;
	@GetMapping("/{key}")
	public ResponseEntity<String> get(@PathVariable String key){
		String value = CacheLrtService.get(key);
		if(value==null) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(value);
	}
	@PutMapping("/{key}")
	public ResponseEntity<Void> put(@PathVariable String key, @RequestBody String value){
		CacheLrtService.put(key,value);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<Void> delete(@PathVariable String key){
		CacheLrtService.delete(key);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/clear/{key}")
	public ResponseEntity<Void> clear(){
		CacheLrtService.clear();
		return ResponseEntity.noContent().build();
	}
	
	
}
