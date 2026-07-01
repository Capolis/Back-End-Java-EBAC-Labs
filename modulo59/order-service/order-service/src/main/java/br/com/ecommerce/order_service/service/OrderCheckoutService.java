package br.com.ecommerce.order_service.service;

import org.springframework.stereotype.Service;

import br.com.ecommerce.order_service.client.CustomerClient;
import br.com.ecommerce.order_service.client.InventoryClient;
import br.com.ecommerce.order_service.client.PaymentClient;
import br.com.ecommerce.order_service.dto.OrderRequest;
import br.com.ecommerce.order_service.dto.OrderResponse;
import br.com.ecommerce.order_service.dto.PaymentRequestDTO;
import br.com.ecommerce.order_service.model.Order;
import br.com.ecommerce.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderCheckoutService {

    // Clientes Feign ou RestTemplate para comunicação HTTP
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    // Repositório local do domínio de Pedidos
    private final OrderRepository orderRepository;

    public OrderCheckoutService(CustomerClient customerClient, InventoryClient inventoryClient,
            OrderRepository orderRepository, PaymentClient paymentClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }

    /**
     * Fluxo principal que integra os 4 microsserviços.
     * O Circuit Breaker monitora falhas neste método.
     */
    @CircuitBreaker(name = "checkoutFlow", fallbackMethod = "checkoutFallback")
    public OrderResponse processCheckout(OrderRequest request) {

        // Validar Cliente (Customer Service)
        customerClient.validateCustomer(request.getCustomerId());

        // Abater Estoque (Inventory Service)
        inventoryClient.reserveProduct(request.getProductId(), request.getQuantity());

        // Processar Pagamento (Payment Service)
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(request.getCustomerId(), request.getTotalAmount());
        paymentClient.processPayment(paymentRequest);

        // Salvar Pedido no Domínio (Order Service - Tabela tb_order)
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setStatus("APPROVED");
        orderRepository.save(order);

        return new OrderResponse(order.getId(), order.getStatus(), "Pedido processado com sucesso!");
    }

    /**
     * Método Fallback: Executado se qualquer serviço externo falhar ou o circuito
     * estiver aberto.
     * A assinatura do método deve ser idêntica ao original, adicionando a exceção
     * no final.
     */
    public OrderResponse checkoutFallback(OrderRequest request, Throwable throwable) {

        // Estratégia de degradação graciosa (Graceful Degradation):
        // Em vez de retornar um erro 500 para o usuário, salvamos o pedido como
        // PENDENTE.
        // Um processo assíncrono posterior (Job) pode tentar processar o pagamento
        // novamente.

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setStatus("PENDING_MANUAL_REVIEW"); // Status indicando que algo falhou e precisa de revisão
        orderRepository.save(order);

        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                "Recebemos seu pedido, mas estamos com instabilidade. Ele será processado em breve.");
    }
}