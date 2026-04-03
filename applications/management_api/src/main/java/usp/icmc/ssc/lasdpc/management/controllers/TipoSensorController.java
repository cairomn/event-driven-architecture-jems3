package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.icmc.ssc.lasdpc.management.controllers.responses.RestResponse;
import usp.icmc.ssc.lasdpc.management.dtos.TipoSensorDTO;
import usp.icmc.ssc.lasdpc.management.services.SensorService;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-sensores")
public class TipoSensorController extends AbstractController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_VIEWER')")
    public RestResponse<List<TipoSensorDTO>> getTiposSensores() {
        return new RestResponse<>(HttpStatus.OK, Constantes.HTTP_CODE_OK, Constantes.MSG_RESP_CONSULTA,
                this.sensorService.getTiposSensores());
    }
}
