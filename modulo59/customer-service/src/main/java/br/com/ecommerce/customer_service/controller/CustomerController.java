package br.com.ecommerce.customer_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.customer_service.dto.CustomerRequestDTO;
import br.com.ecommerce.customer_service.model.Customer;
import br.com.ecommerce.customer_service.repository.CustomerRepository;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    // Injecao de dependencia via construtor
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Endpoint consumido pelo Order Service
    @GetMapping("/{id}/validate")
    public ResponseEntity<Void> validateCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        // Verifica se o cliente existe e se o status active e verdadeiro
        if (customer.isPresent() && customer.get().getActive()) {
            return ResponseEntity.ok().build(); // Retorna HTTP 200 OK
        }

        return ResponseEntity.badRequest().build(); // Retorna HTTP 400 caso contrario
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        // Busca todos os registros na tabela tb_customer
        List<Customer> customers = customerRepository.findAll();
        
        // Retorna a lista com HTTP 200 OK
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestDTO request) {
        // Converte o DTO para a Entidade
        Customer newCustomer = new Customer(request.getName(), request.getActive());

        // Salva no banco de dados
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Retorna HTTP 201 Created com os dados salvos
        return ResponseEntity.status(201).body(savedCustomer);
    }
}