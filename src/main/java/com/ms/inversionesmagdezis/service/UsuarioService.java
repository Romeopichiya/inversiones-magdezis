package main.java.com.ms.inversionesmagdezis.service;

import main.java.com.ms.inversionesmagdezis.model.Usuario;
import main.java.com.ms.inversionesmagdezis.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;

// Nombre en PascalCase
public class UsuarioService {

    // Instanciamos el repositorio para poder comunicarnos con la base de datos
    private UsuarioRepository usuarioRepository;

    // Constructor
    public UsuarioService() {
        // Inicializamos el repositorio
        this.usuarioRepository = new UsuarioRepository();
    }

    // Método en camelCase para procesar el registro
    public boolean registrarUsuario(Usuario usuario) {
        
        // 1. Encriptamos la contraseña usando BCrypt antes de enviarla a la BD
        // gensalt() genera una "sal" aleatoria (caracteres extra) para hacer el hash mucho más seguro
        String contrasenaEncriptada = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
        
        // 2. Le asignamos al usuario su nueva contraseña encriptada
        usuario.setContrasena(contrasenaEncriptada);
        
        // 3. Ahora sí, le pasamos el usuario al Repositorio para que lo guarde en MySQL
        return usuarioRepository.registrarUsuario(usuario);
    }

    // Método en camelCase para procesar el inicio de sesión
    public Usuario iniciarSesion(String correo, String contrasenaIngresada) {
        
        // 1. Le pedimos al repositorio que busque al usuario por su correo
        Usuario usuarioEncontrado = usuarioRepository.buscarUsuarioPorCorreo(correo);
        
        // 2. Si el usuario existe en la base de datos, procedemos a verificar la contraseña
        if (usuarioEncontrado != null) {
            
            // 3. checkpw() compara la contraseña que escribió el usuario (texto plano) 
            // con la contraseña encriptada que trajimos de la base de datos.
            if (BCrypt.checkpw(contrasenaIngresada, usuarioEncontrado.getContrasena())) {
                // Si coinciden, retornamos los datos del usuario (Login exitoso)
                return usuarioEncontrado;
            }
        }
        
        // Si el correo no existe o la contraseña no coincide, retornamos null (Login fallido)
        return null;
    }
}
