package br.com.processing_service.repository;

import br.com.processing_service.model.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {
    // Interface para operações de CRUD orientadas a documentos no MongoDB
}