package usp.icmc.ssc.lasdpc.labstreamcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.labstreamcontrol.controllers.responses.PagedResponse;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.StatusCheck;
import usp.icmc.ssc.lasdpc.labstreamcontrol.services.AtuadorService;
import usp.icmc.ssc.lasdpc.labstreamcontrol.utils.Constantes;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/atuadores")
public class AtuadorController {

    @Autowired
    private AtuadorService atuadorService;

    @GetMapping("/{id}/medicoes")
    public PagedResponse<Medicao> getMediacoes(
        @PathVariable("id") UUID id,
        @RequestParam(name = "pagNum", defaultValue = Constantes.NUMERO_PAGINA_PADRAO, required = false) int pagNum,
        @RequestParam(name = "pagSize", defaultValue = Constantes.TAMANHO_PAGINA_PADRAO, required = false) int pagSize
    ) {
        Page<Medicao> page = this.atuadorService.getMediacoes(id, pagNum, pagSize);
        PagedResponse<Medicao> response = new PagedResponse<>();
        response.setContent(page.getContent());
        response.setLast(page.isLast());
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        return response;
    }

    @PostMapping("/statuses")
    public List<StatusCheck> getStatus(@RequestBody List<UUID> ids) {
        return this.atuadorService.getStatus(ids);
    }
}
