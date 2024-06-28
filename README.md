1. Executar docker compose:
   ```bash
    docker compose up -d
    ```

2. Inicie a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

3. Teste a API de reserva de quartos acessando as URLs:

    - Para pesquisar hotéis:
        ```http
        GET http://localhost:8080/search?destination=Paris&rooms=1&guests=1
        ```

    - Para obter avaliações de um hotel específico:
        ```http
        GET http://localhost:8080/reviews?hotelId=1
        ```

    - Para criar uma reserva:
        ```http
        POST http://localhost:8080/reserve?hotelId=1
        Content-Type: application/json
        {
            "guestName": "John Doe",
            "contact": "john@example.com",
            "paymentDetails": "1234-5678-9012-3456",
            "checkInDate": "2023-10-01",
            "checkOutDate": "2023-10-05",
            "numberOfRooms": 2
        }
        ```

## **Testes**

1. Execute os testes:
    ```bash
    ./mvnw test
    ```
