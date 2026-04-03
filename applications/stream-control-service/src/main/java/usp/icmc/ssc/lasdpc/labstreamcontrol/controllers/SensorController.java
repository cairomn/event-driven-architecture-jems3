package usp.icmc.ssc.lasdpc.labstreamcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.labstreamcontrol.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.services.SensorService;
import usp.icmc.ssc.lasdpc.labstreamcontrol.utils.Constantes;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/{id}/medicoes")
    public PagedResponse<Medicao> getMediacoes(
        @PathVariable("id") UUID id,
        @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
        @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize
    ) {
        Page<Medicao> page = this.sensorService.getMediacoes(id, pagNum, pagSize);
        PagedResponse<Medicao> response = new PagedResponse<>();
        response.setContent(page.getContent());
        response.setLast(page.isLast());
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        return response;
    }

}
