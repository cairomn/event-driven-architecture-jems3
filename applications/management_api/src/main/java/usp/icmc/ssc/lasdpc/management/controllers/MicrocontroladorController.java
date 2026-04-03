package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.ActionDTO;
import usp.icmc.ssc.lasdpc.management.dtos.MicrocontroladorDTO;
import usp.icmc.ssc.lasdpc.management.entities.Atuador;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sensor;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.services.MicrocontroladorService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/microcontroladores")
public class MicrocontroladorController extends AbstractController {

    @Autowired
    private MicrocontroladorService microcontroladorService;

    @GetMapping
    public List<Microcontrolador> get() {
        return this.microcontroladorService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<MicrocontroladorDTO> create(@RequestBody Microcontrolador microcontrolador)
            throws BusinessException, NotFoundRegisterException {
        try {
            Microcontrolador save = this.microcontroladorService.save(microcontrolador);
            return new RestResponse<>(HttpStatus.CREATED, Constantes.HTTP_CODE_CREATED, Constantes.MSG_RESP_CADASTRO,
                    MicrocontroladorDTO.from(save));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{id}/on-off-air-cond")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<Object> changeStatusAirConditioner(@RequestBody ActionDTO action) throws RuntimeException {
        try {
            this.microcontroladorService.changeStatusAirCond(action);
            return new RestResponse<>(
                    HttpStatus.OK,
                    Constantes.HTTP_CODE_OK,
                    Constantes.MSG_RESP_PUBLICADO,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse<>(HttpStatus.BAD_REQUEST, Constantes.HTTP_CODE_NOT_FOUND,
                    e.getMessage());
        }
    }

    @PostMapping("/{id}/change-temp-air-cond")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<Object> changeTempAirConditioner(@RequestBody ActionDTO action) throws RuntimeException {
        try {
            this.microcontroladorService.changeTempAirCond(action);
            return new RestResponse<>(
                    HttpStatus.OK,
                    Constantes.HTTP_CODE_OK,
                    Constantes.MSG_RESP_PUBLICADO,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse<>(HttpStatus.BAD_REQUEST, Constantes.HTTP_CODE_NOT_FOUND,
                    e.getMessage());
        }
    }
    
    @PostMapping("/{id}/change-mode-air-cond")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<Object> changeModeAirConditioner(@RequestBody ActionDTO action) throws RuntimeException {
        try {
            this.microcontroladorService.changeModeAirCond(action);
            return new RestResponse<>(
                    HttpStatus.OK,
                    Constantes.HTTP_CODE_OK,
                    Constantes.MSG_RESP_PUBLICADO,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse<>(HttpStatus.BAD_REQUEST, Constantes.HTTP_CODE_NOT_FOUND,
                    e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<MicrocontroladorDTO> getById(@PathVariable("id") final UUID id)
            throws NotFoundRegisterException {
        try {
            Microcontrolador microcontrolador = this.microcontroladorService.find(id);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                    MicrocontroladorDTO.from(microcontrolador));
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<MicrocontroladorDTO> update(
            @RequestBody Microcontrolador microcontrolador,
            @PathVariable("id") UUID id) throws BusinessException, NotFoundRegisterException {
        try {
            Microcontrolador updated = this.microcontroladorService.save(microcontrolador);
            return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_ALTERACAO,
                    MicrocontroladorDTO.from(updated));
        } catch (BusinessException e) {
            return new RestResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, Constantes.HTTP_CODE_UNPROCESSABLE_ENTITY,
                    e.getMessage());
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public RestResponse<MicrocontroladorDTO> delete(@PathVariable("id") UUID id) throws NotFoundRegisterException {
        try {
            this.microcontroladorService.delete(id);
            return new RestResponse<>(HttpStatus.NO_CONTENT, Constantes.HTTP_CODE_NO_CONTENT, Constantes.MSG_RESP_REMOVIDO);
        } catch (NotFoundRegisterException e) {
            return new RestResponse<>(HttpStatus.NOT_FOUND, Constantes.HTTP_CODE_NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}/sensores")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Sensor> getSensores(@PathVariable("id") UUID id) {
        return this.microcontroladorService.getSensores(id);
    }

    @GetMapping("/{id}/atuadores")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Atuador> getAtuadores(@PathVariable("id") UUID id) {
        return this.microcontroladorService.getAtuadores(id);
    }
}
