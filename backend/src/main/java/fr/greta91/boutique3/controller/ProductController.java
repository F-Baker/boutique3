package fr.greta91.boutique3.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.boutique3.model.Category;
import fr.greta91.boutique3.model.Product;
import fr.greta91.boutique3.repos.CategoryRepository;
import fr.greta91.boutique3.repos.ProductRepository;

//@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@GetMapping("")
	//@RequestMapping(method= RequestMethod.GET)
	public List<Product> getProducts() {
		List<Product> list = productRepo.findAll();

		return list;
	}

	@GetMapping("/categories")
	public List<Category> getCategories() {
		List<Category> list = categoryRepo.findAll();

		return list;
	}
	
	@GetMapping("/productName/{productName}")
	//@RequestMapping(value = "/productName/{productName}", method = RequestMethod.GET)
	public List<Product> getProductsByName(@PathVariable String productName) {
		List<Product> list = productRepo.findByProductName(productName);
		return list;
	}

	
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//public ResponseEntity<Product> getProduct(@RequestParam Integer id) {
	@GetMapping("/{id}")
	//public ResponseEntity<Product> getProduct(@RequestParam(value="id") int id){
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {

			/*
			 * HttpHeaders responseHeaders = new HttpHeaders();
			 * responseHeaders.add("Access-Control-Allow-Origin", "http://localhost:8080");
			 * responseHeaders.add("Content-type", "application/json");
			 * responseHeaders.add("Accept", "application/json");
			 * responseHeaders.add("Authorization", "*");
			 * 
			 * Product res = productRepo.save(product); return
			 * ResponseEntity.ok().headers(responseHeaders).body(res);
			 */
			Product res = productRepo.save(product);
			return ResponseEntity.ok(res);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}") // A revoir url
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		try {
			Product product = productRepo.findById(id).get();
			if (null != product) {
				productRepo.delete(product);
				return ResponseEntity.ok(product);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception ex) {
			System.out.println("In ProductController.deleteProduct(), exception : " + ex); // not sure if needed
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/edit/{id}") // A revoir url
	public ResponseEntity<Product> modifyProduct(@PathVariable int id, @RequestBody Product product) {
		try {
//			HttpHeaders responseHeaders = new HttpHeaders();
//			responseHeaders.add("Access-Control-Allow-Origin", "http://localhost:8080");
//			responseHeaders.add("Content-type", "application/json");
//			responseHeaders.add("Accept", "application/json");
//			responseHeaders.add("Authorization", "*");
//		    responseHeaders.set("Content-type: application/json", "Access-Control-Allow-Origin: http://localhost:8080");
			
//			Product res = productRepo.save(product);
//			return ResponseEntity.ok().headers(responseHeaders).body(res);
			Product res = productRepo.save(product);
			return ResponseEntity.ok(res);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

}
