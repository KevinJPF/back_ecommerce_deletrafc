package fatec.les.ecommerce.controller;

import fatec.les.ecommerce.fachada.EnderecoFachada;
import fatec.les.ecommerce.model.Endereco;
import fatec.les.ecommerce.model.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class EnderecoController {
    @GetMapping(value="/enderecos")
    public ResponseEntity<?> selectEntities() {
        List<DomainEntity> enderecos = EnderecoFachada.getInstance().selectEntities();
        if (enderecos == null) {
            return new ResponseEntity<>("Erro.", HttpStatus.NOT_FOUND);
        } else
        if (enderecos.size() == 0) {
            return new ResponseEntity<>("Não há enderecos cadastrados ainda.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping(value="/endereco")
    public ResponseEntity<?> selectEntity(@RequestParam Integer id) {
        try {
            DomainEntity endereco = EnderecoFachada.getInstance().selectEntity(id);
            if (endereco == null) {
                return new ResponseEntity<>("Endereco não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Endereco não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/endereco")
    public ResponseEntity<String> insertEntity(@RequestBody Endereco endereco) {
        String response = EnderecoFachada.getInstance().insertEntity(endereco);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Endereco inserido com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Erro ao inserir endereco: \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/endereco")
    public ResponseEntity<String> updateEntity(@RequestBody Endereco endereco) {
        String response = EnderecoFachada.getInstance().updateEntity(endereco);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Endereco atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao atualizar endereco:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Integer id) {
        String response = EnderecoFachada.getInstance().deleteEntity(id);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Endereco excluído com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao excluir endereco:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }
}
