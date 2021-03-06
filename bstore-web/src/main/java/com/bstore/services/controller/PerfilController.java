package com.bstore.services.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.model.ErrorService;
import com.bstore.services.model.SessionConstants;
import com.bstore.services.model.UserSession;
import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.EnviarEmailService;
import com.bstore.services.service.PerfilService;
import com.bstore.services.service.PropertyService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.UtilService;
import com.bstore.services.util.ValidarSesion;

@Controller
public class PerfilController {

    private final Logger log = Logger.getLogger(PerfilController.class);
    private static final String NAME_CONTROLLER = "[PerfilController]";
    private final String EMAIL_SYSTEM = "com.bstore.mail.app.bcc";
    private final String DOMAIN_BSTORE = "com.domain.quivira";
    private final String USER_EMAIL_SYSTEM = "com.email.system";
    private final String PASSWORD_EMAIL_SYSTEM = "com.password.system";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    EnviarEmailService enviarEmailService;

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public String perfil(Model model, HttpServletRequest request) {
        log.info("Cargando perfil de usuario: " + NAME_CONTROLLER);

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        Usuario user = this.usuarioService.byIdUser(usuario.getId());
        model.addAttribute("user", user);
        return "perfilUsuario";

    }

    @RequestMapping(value = "/perfil/actualizar", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> perfilActualizar(Model model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Actualizando perfil de usuario: " + NAME_CONTROLLER);

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorService responseLocal = new ErrorService();

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            responseLocal.setCodigo("403");
            responseLocal.setMensaje("Se ha vencido la sesion del usuario");
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<ErrorService>(responseLocal, status);
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        String idUsuario = request.getParameter("idUsuario");
        String nombreUsuario = request.getParameter("nombre");
        String apaternoUsuario = request.getParameter("apaterno");
        String amaternoUsuario = request.getParameter("amaterno");
        String emailUsuario = request.getParameter("email");
        String login = request.getParameter("login");
        String telefono = request.getParameter("telefono");
        String passwordUsuario1 = request.getParameter("pass1");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String actividad = request.getParameter("actividad");
        String sexo = request.getParameter("sexo");
        String notificar = request.getParameter("notificar");

        Usuario user = this.usuarioService.byIdUser(Integer.valueOf(idUsuario));
        Usuario userTemporal = this.usuarioService.validaEmailSistema(emailUsuario);
        Usuario userTemporalLogin = this.usuarioService.validaLoginSistema(login);
        if (userTemporal != null && !user.getEmail().equalsIgnoreCase(userTemporal.getEmail())) {
            responseLocal.setCodigo("404");
            responseLocal.setMensaje("Este email ya ha sido utilizado, " + emailUsuario);
        } else if (userTemporalLogin != null && !user.getLogin().equalsIgnoreCase(userTemporalLogin.getLogin())) {
            responseLocal.setCodigo("404");
            responseLocal.setMensaje("Este login ya ha sido utilizado, " + login);
        } else if (user != null) {
            this.log.info("Usuario encontrado: " + user.toString());
            if (!user.getPassword().equalsIgnoreCase(passwordUsuario1)) {
                String encriptarPassword = UtilService.Encriptar(passwordUsuario1);
                user.setPassword(encriptarPassword);
            }
            user.setNombre(nombreUsuario);
            user.setLogin(login);
            user.setAPaterno(apaternoUsuario);
            user.setAMaterno(amaternoUsuario);
            user.setActividad(actividad);
            user.setSexo(sexo);
            user.setNotificaciones(notificar != null ? notificar : "NO");
            user.setUltConexion(new Date());
            user.setTelefono(telefono);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dia + "/" + mes + "/" + anio;
            try {
                Date date = formatter.parse(dateInString);
                this.log.info("Año de Nacimiento: " + date);
                if (LoginController.validaFecha(dateInString)) {
                    this.log.info("Fecha es Valida: " + date);
                    user.setFechaNacimiento(date);
                } else {
                    this.log.info("Fecha Invalida: " + dateInString);
                    ErrorService data = new ErrorService();
                    data.setCodigo("404");
                    data.setMensaje("La fecha de nacimiento es inválida: " + dateInString);
                    return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                this.log.error("Error al crear la fecha de nacimiento: " + e.getMessage());
            }

            this.usuarioService.actualizarDatosUsuario(user);

            this.log.info("El usuario fue actualizado");
            responseLocal.setCodigo("200");
            responseLocal.setMensaje("La información fue actualizada con éxito.");
            status = HttpStatus.OK;

            /**
             * Se actualiza datos se sesion del usuario
             */
            usuario.setId(user.getId());
            usuario.setNombre(user.getNombre());
            usuario.setPaterno(user.getAPaterno());
            usuario.setEmail(user.getEmail());
            usuario.setTelefono(user.getTelefono());
            usuario.setPerfil(user.getPerfil().getNombre());

            model.addAttribute("user", user);
            session.setAttribute(SessionConstants.USUARIO, usuario);
        }

        return new ResponseEntity<ErrorService>(responseLocal, status);
    }

    @RequestMapping(value = "/perfil/cancelar", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> perfilCancelar(Model model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cancelar cuenta de perfil de usuario: " + NAME_CONTROLLER);

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorService responseLocal = new ErrorService();

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            responseLocal.setCodigo("403");
            responseLocal.setMensaje("Se ha vencido la sesion del usuario");
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<ErrorService>(responseLocal, status);
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        String idUsuario = request.getParameter("idUsuario");
        Usuario user = this.usuarioService.byIdUser(Integer.valueOf(idUsuario));
        if (user != null) {
            try {
                this.log.info("Url request actual::: " + request.getRequestURL());
                this.log.info("Url request actual::: " + request.getRequestURL());
                String urlServer = this.propertyService.getValueKey(DOMAIN_BSTORE).getValue();
                String emailEncriptado = UtilService.Encriptar(user.getEmail());
                String nuevaUrlParaConfirmacion = urlServer + "/"
                        + "confirmarBajaDeTuCuenta?token=" + emailEncriptado;

                this.log.info("Url para baja de cuenta " + user.getEmail() + " URL === " + nuevaUrlParaConfirmacion);
                this.enviarEmailService.enviarEmailBaja(user.getEmail(), this.propertyService.getValueKey(EMAIL_SYSTEM).getValue().split(";"), usuario, nuevaUrlParaConfirmacion,
                        this.propertyService.getValueKey(USER_EMAIL_SYSTEM).getValue(),
                        this.propertyService.getValueKey(PASSWORD_EMAIL_SYSTEM).getValue());
                this.log.info("Enviado");
                this.log.info("Se envia a usuario confirmacion para cancelacion de cuenta: " + user.getEmail());
                responseLocal.setCodigo("200");
                responseLocal.setMensaje("Se ha enviado un correo a tu cuenta, para que confirmes la baja del servicio.");
                status = HttpStatus.OK;
            } catch (Exception ex) {
                ex.printStackTrace();
                this.log.error("No se puedo enviar mail de baja de cuenta: " + ex.getMessage());
                responseLocal.setCodigo("404");
                responseLocal.setMensaje("Por el momento no ha sido posible continuar con el proceso, intenta más tarde.");
                return new ResponseEntity<ErrorService>(responseLocal, HttpStatus.NOT_FOUND);
            }
        } else {
            this.log.info("Usuario no tiene cuenta valida");
            responseLocal.setCodigo("404");
            responseLocal.setMensaje("Por el momento no ha sido posible continuar con el proceso, intenta más tarde.");
            return new ResponseEntity<ErrorService>(responseLocal, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ErrorService>(responseLocal, status);
    }

    @RequestMapping(value = "perfil/getAll", method = RequestMethod.GET)
    public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("PerfilController.getAll(): " + NAME_CONTROLLER + "/getAll");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        List<Perfil> perfilList = perfilService.getAll();
        if (perfilList != null) {
            model.addAttribute("perfiles", perfilList);
        }
        model.addAttribute("perfil", new Perfil());

        return "perfilesAdmin";
    }
}
