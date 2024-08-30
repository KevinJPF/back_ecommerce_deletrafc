package fatec.les.ecommerce.controller;

import fatec.les.ecommerce.fachada.ClienteFachada;
import fatec.les.ecommerce.model.Cliente;
import fatec.les.ecommerce.model.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class ClienteController {
    @GetMapping(value="/clientes")
    public ResponseEntity<?> selectEntities() {
        List<DomainEntity> clientes = ClienteFachada.getInstance().selectEntities();
        if (clientes == null) {
            return new ResponseEntity<>("Erro.", HttpStatus.NOT_FOUND);
        } else
        if (clientes.size() == 0) {
            return new ResponseEntity<>("Não há clientes cadastrados ainda.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(value="/cliente")
    public ResponseEntity<?> selectEntity(@RequestParam Integer id) {
        try {
            DomainEntity cliente = ClienteFachada.getInstance().selectEntity(id);
            if (cliente == null) {
                return new ResponseEntity<>("Cliente não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cliente não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<String> insertEntity(@RequestBody Cliente cliente) {
        String response = ClienteFachada.getInstance().insertEntity(cliente);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cliente inserido com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Erro ao inserir cliente: \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity<String> updateEntity(@RequestBody Cliente cliente) {
        String response = ClienteFachada.getInstance().updateEntity(cliente);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao atualizar cliente:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Integer id) {
        String response = ClienteFachada.getInstance().deleteEntity(id);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cliente excluído com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao excluir cliente:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cliente/alterar-senha")
    public ResponseEntity<String> changePassword(@RequestBody Cliente cliente) {
        String response = ClienteFachada.getInstance().changePassword(cliente);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Senha alterada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao alterar senha:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cliente/ativar")
    public ResponseEntity<String> activateCliente(@RequestBody Cliente cliente) {
        String response = ClienteFachada.getInstance().activateCliente(cliente);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cliente ativado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao ativar cliente:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cliente/inativar")
    public ResponseEntity<String> inactivateCliente(@RequestBody Cliente cliente) {
        String response = ClienteFachada.getInstance().inactivateCliente(cliente);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cliente inativado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao inativar cliente:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }
}
