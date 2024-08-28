package fatec.les.ecommerce.controller;

import fatec.les.ecommerce.fachada.CartaoCreditoFachada;
import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.CartaoCredito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class CartaoCreditoController {
    @GetMapping(value="/cartoes")
    public ResponseEntity<?> selectEntities() {
        List<DomainEntity> cartoes = CartaoCreditoFachada.getInstance().selectEntities();
        if (cartoes == null) {
            return new ResponseEntity<>("Erro.", HttpStatus.NOT_FOUND);
        } else
        if (cartoes.size() == 0) {
            return new ResponseEntity<>("Não há cartoes cadastrados ainda.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartoes, HttpStatus.OK);
    }

    @GetMapping(value="/cartao")
    public ResponseEntity<?> selectEntity(@RequestParam Integer id) {
        try {
            DomainEntity cartao = CartaoCreditoFachada.getInstance().selectEntity(id);
            if (cartao == null) {
                return new ResponseEntity<>("Cartao de credito não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cartao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cartao de credito não encontrado com o ID fornecido.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cartao")
    public ResponseEntity<String> insertEntity(@RequestBody CartaoCredito cartao) {
        String response = CartaoCreditoFachada.getInstance().insertEntity(cartao);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cartao de credito inserido com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Erro ao inserir cartao: \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cartao")
    public ResponseEntity<String> updateEntity(@RequestBody CartaoCredito cartao) {
        String response = CartaoCreditoFachada.getInstance().updateEntity(cartao);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cartao de credito atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao atualizar cartao:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cartao/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Integer id) {
        String response = CartaoCreditoFachada.getInstance().deleteEntity(id);
        if (response.contains("sucesso")) {
            return new ResponseEntity<>("Cartao de credito excluído com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao excluir cartao:  \n" + response, HttpStatus.BAD_REQUEST);
        }
    }
}
