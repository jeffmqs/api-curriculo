package com.exemplo.curriculoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.curriculoapi.model.Curriculo;
import com.exemplo.curriculoapi.model.FormacaoAcademica;
import com.exemplo.curriculoapi.repository.CurriculoRepository;

@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    @Autowired
    private CurriculoRepository curriculoRepository;

    // Método GET para listar todos os currículos
    @GetMapping
    public List<Curriculo> listarTodos() {
        return curriculoRepository.findAll();
    }

    // Método GET para obter um currículo específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> obterCurriculoPorId(@PathVariable Long id) {
        return curriculoRepository.findById(id)
                .map(curriculo -> ResponseEntity.ok().body(curriculo))
                .orElse(ResponseEntity.notFound().build());
    }

    // Método POST para criar um novo currículo
    @PostMapping
    public Curriculo criarCurriculo(@RequestBody Curriculo novoCurriculo) {
        novoCurriculo.getFormacaoAcademica().forEach(formacao -> formacao.setCurriculo(novoCurriculo));
        novoCurriculo.getExperienciaProfissional().forEach(experiencia -> experiencia.setCurriculo(novoCurriculo));
        return curriculoRepository.save(novoCurriculo);
    }

    // Método PUT para atualizar qualquer campo de um currículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> atualizarCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculoAtualizado) {
        return curriculoRepository.findById(id).map(curriculoExistente -> {

            // Atualiza o nome, telefone e email
            if (curriculoAtualizado.getNome() != null) curriculoExistente.setNome(curriculoAtualizado.getNome());
            if (curriculoAtualizado.getTelefone() != null) curriculoExistente.setTelefone(curriculoAtualizado.getTelefone());
            if (curriculoAtualizado.getEmail() != null) curriculoExistente.setEmail(curriculoAtualizado.getEmail());

            // Atualiza a formação acadêmica
            if (curriculoAtualizado.getFormacaoAcademica() != null) {
                curriculoExistente.getFormacaoAcademica().clear();
                for (FormacaoAcademica formacao : curriculoAtualizado.getFormacaoAcademica()) {
                    formacao.setCurriculo(curriculoExistente);
                    curriculoExistente.getFormacaoAcademica().add(formacao);
                }
            }

            // Salva o currículo atualizado no banco de dados
            Curriculo atualizado = curriculoRepository.save(curriculoExistente);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método DELETE para remover um currículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCurriculo(@PathVariable Long id) {
        return curriculoRepository.findById(id).map(curriculo -> {
            curriculoRepository.delete(curriculo);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
