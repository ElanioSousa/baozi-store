package com.baozistore.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baozistore.api.model.Pedido;
import com.baozistore.api.repository.ClienteRepository;
import com.baozistore.api.repository.PedidoRepository;
import com.baozistore.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository repository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    // POST /pedidos - registra um pedido
    // So aceita se o cliente e o produto existirem e a quantidade for maior que zero
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Pedido pedido) {
        String erro = validar(pedido);
        if (erro != null) {
            return ResponseEntity.badRequest().body(erro);
        }
        pedido.setId(null);
        Pedido salvo = repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /pedidos - lista todos
    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    // GET /pedidos/{id} - consulta por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /pedidos/{id} - atualiza (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pedido dados) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        String erro = validar(dados);
        if (erro != null) {
            return ResponseEntity.badRequest().body(erro);
        }
        dados.setId(id);
        return ResponseEntity.ok(repository.save(dados));
    }

    // DELETE /pedidos/{id} - apaga
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private String validar(Pedido pedido) {
        if (pedido.getClienteId() == null || !clienteRepository.existsById(pedido.getClienteId())) {
            return "Cliente nao encontrado. Informe um clienteId valido.";
        }
        if (pedido.getProdutoId() == null || !produtoRepository.existsById(pedido.getProdutoId())) {
            return "Produto nao encontrado. Informe um produtoId valido.";
        }
        if (pedido.getQuantidade() == null || pedido.getQuantidade() <= 0) {
            return "A quantidade deve ser maior que zero.";
        }
        return null;
    }
}
