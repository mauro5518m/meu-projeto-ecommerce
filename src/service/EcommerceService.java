package service;

import model.Usuario;
import repository.UsuarioRepository;
import java.util.List;

public class EcommerceService {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void registarUsuario(Usuario usuario) {
        // Aqui podiam entrar validações de negócio (ex: verificar se o email já existe)
        usuarioRepository.salvar(usuario);
    }

    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.listarTodos();
    }
}